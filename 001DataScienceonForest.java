package ai.certifai.solution.EXERCISE.forest;

import org.datavec.api.records.reader.impl.collection.CollectionRecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.datavec.api.transform.TransformProcess;
import org.datavec.api.transform.schema.Schema;
import org.datavec.api.writable.Writable;
import org.datavec.local.transforms.LocalTransformExecutor;
import org.deeplearning4j.core.storage.StatsStorage;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.deeplearning4j.ui.api.UIServer;
import org.deeplearning4j.ui.model.stats.StatsListener;
import org.deeplearning4j.ui.model.storage.InMemoryStatsStorage;
import org.nd4j.common.io.ClassPathResource;
import org.nd4j.evaluation.regression.RegressionEvaluation;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.SplitTestAndTrain;
import org.nd4j.linalg.dataset.ViewIterator;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataScienceonForest {

    private static int batchSize =256;
    private static int seed =123;
    private static int nEpochs = 2000; // option 1,2

    public static void main(String[] args) throws IOException, InterruptedException {


        File file = new ClassPathResource("forest_burn/forest_v2.csv").getFile();
        FileSplit split = new FileSplit(file);

        CSVRecordReader csv = new CSVRecordReader(1,',');
        csv.initialize(split);

        System.out.println(csv.next().size());   //This will get number of columns

        Schema schema = new Schema.Builder()
                .addColumnsInteger("index","month","day")
                .addColumnsDouble("FFMC","DMC","DC","temp")
                .addColumnInteger("RH")
                .addColumnsDouble("wind","hypotenuse", "burned_area")
                .build();

        TransformProcess tp = new TransformProcess.Builder(schema)
                .removeColumns("index") //DMC is optional to be removed
                .build();


        List<List<Writable>> orig_data = new ArrayList<>();
        while (csv.hasNext()){
            List<Writable> data = csv.next();
            orig_data.add(data);

        }

        List<List<Writable>> trasnfomedData  = LocalTransformExecutor.execute(orig_data,tp);

        // the number of rows in data excluding the name of columns
        System.out.println(trasnfomedData.size());

        int index;
        for(index=0; index< trasnfomedData.size();index++) {
            System.out.println(trasnfomedData.get(index));
        }

        CollectionRecordReader collectionRR = new CollectionRecordReader(trasnfomedData);
        DataSetIterator iter = new RecordReaderDataSetIterator(collectionRR, trasnfomedData.size(),9,9, true);

        DataNormalization normalizer = new NormalizerStandardize();
        normalizer.fit(iter);           //Collect the statistics (mean/stdev) from the training data. This does not modify the input data
        iter.setPreProcessor(normalizer);


        DataSet allData  = iter.next();
        allData.shuffle();

        SplitTestAndTrain splitting = allData.splitTestAndTrain(0.7);

        DataSet training = splitting.getTrain();
        DataSet testing = splitting.getTest();

        ViewIterator trainIter = new ViewIterator(training, batchSize);
        ViewIterator testIter = new ViewIterator(testing, batchSize);


        MultiLayerConfiguration cfg = new NeuralNetConfiguration.Builder()
                .seed(seed)
                .weightInit(WeightInit.XAVIER)
                .updater(new Adam(0.001))
                .list()
                .layer(new DenseLayer.Builder()
                        .nIn(9).nOut(64).activation(Activation.RELU)
                        .build())
                .layer(new DenseLayer.Builder()
                        .nIn(64).nOut(128).activation(Activation.RELU)
                        .build()) // option 3
                .layer(new DenseLayer.Builder()
                        .nIn(128).nOut(256).activation(Activation.RELU)
                        .build())
                .layer(new DenseLayer.Builder()
                        .nIn(256).nOut(128).activation(Activation.RELU)
                        .build())
                .layer(new DenseLayer.Builder()
                        .nIn(128).nOut(64).activation(Activation.RELU)
                        .build()) // option 3
                .layer(new DenseLayer.Builder()
                        .nIn(64).nOut(32).activation(Activation.RELU)
                        .build())
                .layer(new OutputLayer.Builder()
                        .nOut(1).activation(Activation.IDENTITY)
                        .lossFunction(LossFunctions.LossFunction.MEAN_ABSOLUTE_ERROR)
                        .build())
                //.setInputType(InputType.feedForward(12))
                .build();

        MultiLayerNetwork model = new MultiLayerNetwork(cfg);

        model.init();

        UIServer uiServe =UIServer.getInstance();
        StatsStorage stast = new InMemoryStatsStorage();
        uiServe.attach(stast);
        model.setListeners(new ScoreIterationListener(100), new StatsListener(stast));

        //  Fitting the model for nEpochs
        for(int i =0; i<nEpochs;i++){
            if(i%1000==0){
                System.out.println("Epoch: " + i);
            }
            model.fit(trainIter);
        }


        RegressionEvaluation regEval = model.evaluateRegression(testIter);
        System.out.println(regEval.stats());

        testIter.reset();

        INDArray targetLabels = testing.getLabels();
        System.out.println("\nTarget shape: " + targetLabels.shapeInfoToString());

        INDArray predictions = model.output(testIter);
        System.out.println("\nPredictions shape: " + predictions.shapeInfoToString() + "\n");

        System.out.println("Target \t\t\t Predicted");

        System.out.println(targetLabels.rows());
        for (int i = 0; i < targetLabels.rows(); i++) {
            System.out.println(targetLabels.getRow(i) + "\t\t" + predictions.getRow(i));
        }







    }
}
