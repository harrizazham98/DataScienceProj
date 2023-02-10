# Germany Vehicle Market

# ![Alt text](materials/buying-car-germany_0.jpg)

## PROBLEM STATEMENT

### As electric vehicles (EV) are becoming more affortable and assessible to consumers, the consideration for purchase of EV over internal combustion engine (ICE) is increasing.Using the data from one of the Europe lastest car market for the past 10 years (2011 – 2021), analyse the data and provide the following insights:

### 1. Trend of EV and ICE purchase in the past 10 years.
### 2. Consumer preferences of used vs new car when purchasing of EV and ICE.
### 3. Prediction of EV and ICE purchase in the next 5 years.
### 4. Best type of EV and ICE preferred in the past and prediction for the future.

## THE CODE:
### eda.ipynb covers Q1, Q2, and Q4
### forecasting.ipynb covers Q3

## INSTALL ENVIRONMENT
### CONDA
1. Make sure you have conda installed in your local system.<br>
2. Open terminal, change the respective directory to 005_CarPurchase folder (contains environment.yml) and run:<br>

         conda env create -f environment.yml 
3. Activate the environment. <br>

         conda activate dataScienceProject
4. Install sktime seprately. <br>

         pip install sktime[all_extras]

5. At your editor such as Visual Studio, make sure you change the kernel to "dataScienceProject" and then you can run the jupyterbook accordingly. <br>

### venv
1. Create your venv and activate it.<br>
2. Run <br>

         pip install -r requirements.txt

## Q1

### Trend of EV purchase in the past 10 years.
![Alt text](materials/Q1/output1.png)<br>
From the above graph, the line fluctuates and it shows a decresing pattern. Starting at 2019, it starts to fall significantly and I assume due to Covid19 pandemics. EU plan to ban fossil-fuel cars (https://www.reuters.com/business/autos-transportation/germany-rejects-eu-plan-ban-new-fossil-fuel-cars-2035-2022-06-21/) may give a slight impact. This decreasing patterns shows that Germany tries to adapt green energy and preserve their environment from any air pollution from ICE engine. <br>

### Trend of ICE purchase in the past 10 years.
![Alt text](materials/Q1/output2.png)<br>
From the above graph, it increases over time. Lots of local car manufacturer of Germany produce lots of EV cars to support the idea of green energy. 

### Comparison
![Alt text](materials/Q1/output3.png)<br>
Real Scale<br>

![Alt text](materials/Q1/output4.png)<br>
Normalized Scale<br>

Both EV and ICE graphs show opposite direction. ICE is still relevant nowadays because EV is still in initial phase. Lots of effots must be done to implement fully electric car such as charging station and offer affordable price. Based on the graph of EV, I believe the sale will be increasing over time.


## Q2

### Consumer preferences of used vs new car when purchasing of EV.
![Alt text](materials/Q2/output1.png)<br>

### Consumer preferences of used vs new car when purchasing of ICE.
![Alt text](materials/Q2/output2.png)<br>

Surprisingly, more than three quarters of consumers prefer Used car for both ICE and New. This is due to introduction of Employee's car scheme and Demo which offer them afforadble price with almost-new car condition. 
## Q3
For forecasting method, I use statistical method (ARIMA, Exponential Smoothing), Machine Learning (Linear Rigression, XGBoost), and Recursive multi-step forecasting

### Prediction of ICE purchase in the next 5 years.
First, Autocorrelation and Partial Correlation is computed to identify the relationship among lags.
![Alt text](materials/Q3/output1.png)<br>
Original Time Series
![Alt text](materials/Q3/output2.png)<br>
1st Order Time Series
![Alt text](materials/Q3/output3.png)<br>
2nd Order Time Series<br>

From the Autocorrelation and Partial Correlation of all condition, the time series is still non-stationary. The time series must be stationary before applying it to forecasting method because the mean and variance must be constant over time or series.<br>

#### ARIMA
![Alt text](materials/Q3/output4.png)<br>

#### Exponential Smoothing
![Alt text](materials/Q3/output5.png)<br>

#### Machine Learning
![Alt text](materials/Q3/output6.png)<br>
![Alt text](materials/Q3/output7.png)<br>
![Alt text](materials/Q3/output8.png)<br>
![Alt text](materials/Q3/output9.png)<br>
![Alt text](materials/Q3/output10.png)<br>
p/s ML scripts will not issue next 5 years prediciton as I see the algorithm does not show good performance

### Recursive multi-step forecasting
![Alt text](materials/Q3/output11.png)<br>

### XGBOOST
![Alt text](materials/Q3/output12.png)<br>


### Prediction of EV purchase in the next 5 years.
First, Autocorrelation and Partial Correlation is computed to identofi the relationship among lags.
![Alt text](materials/Q3/output13.png)<br>
Original Time Series
![Alt text](materials/Q3/output14.png)<br>
1st Order Time Series

From the Autocorrelation and Partial Correlation of all condition, the time series is still non-stationary. The time seies must be stationary before applying it to forecasting method because the mean and variance must be constant over time or series.<br>

#### ARIMA
![Alt text](materials/Q3/output15.png)<br>

#### Exponential Smoothing
![Alt text](materials/Q3/output16.png)<br>

#### Machine Learning
![Alt text](materials/Q3/output17.png)<br>
![Alt text](materials/Q3/output18.png)<br>
![Alt text](materials/Q3/output19.png)<br>
![Alt text](materials/Q3/output20.png)<br>
![Alt text](materials/Q3/output21.png)<br>
p/s ML scripts will not issue next 5 years prediciton as I see the algorithm does not show good performance

### Recursive multi-step forecasting
![Alt text](materials/Q3/output22.png)<br>

### XGBOOST
![Alt text](materials/Q3/output23.png)<br>

Overall, some forecasting methods did well. Exponential Smoothing did quite well but ARIMA does not perform due to stationary issue. The original time series is non-stationary and differencing method is applied at several orders to make stationary. I try to do it till 2nd order but the time series is still non-stationary. Plus, it is not good idea to increase the order differencing because it reduces time series.


## Q4

### Best type of EV preferred in the past
![Alt text](materials/Q4/output1.png)<br>
In the past, Battery Electric Car has higher percentage than Hybrid.
### Best type of ICE preferred in the past
![Alt text](materials/Q4/output2.png)<br>
In the past, ICE Gasoline Car has higher percentage than ICE Diesel.
### Best type of EV preferred for the future
For prediction, I use Exponential Smoothing as it seems giving a good performance on previous question. 
![Alt text](materials/Q4/output3.png)<br>
Hybrid
![Alt text](materials/Q4/output4.png)<br>
Battery Electric Car

Based on Hybrid and bEV graph, it shows a significant increase while Battery Electric Vehicle shows a slight increase. Hence, Hybrid is predicted to be the best EV type in future.
### Best type of ICE preferred for the future
![Alt text](materials/Q4/output5.png)<br>
Gasoline
![Alt text](materials/Q4/output6.png)<br>
Diesel
Based on Gasoline and Diesel graph, both are decreasing but Diesel shows a drastic decreasing . Hence, Gasoline is expected to best ICE type in future. 












