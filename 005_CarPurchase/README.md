# Germany Vehicle Market

# ![Alt text](materials/buying-car-germany_0.jpg)

## PROBLEM STATEMENT

### As electric vehicles (EV) are becoming more affortable and assessible to consumers, the consideration for purchase of EV over internal combustion engine (ICE) is increasing.Using the data from one of the Europe lastest car market for the past 10 years (2011 – 2021), analyse the data and provide the following insights:

### 1. Trend of EV and ICE purchase in the past 10 years.
### 2. Consumer preferences of used vs new car when purchasing of EV and ICE.
### 3. Prediction of EV and ICE purchase in the next 5 years.
### 4. Best type of EV and ICE preferred in the past and prediction for the future.

## Q1

### Trend of EV purchase in the past 10 years.
![Alt text](materials/Q1/output1.png)<br>
From the above graph, the line fluctuates and it shows a decresing pattern. 

### Trend of ICE purchase in the past 10 years.
![Alt text](materials/Q1/output2.png)<br>
From the above graph, it increases over time. 

### Comparison
![Alt text](materials/Q1/output3.png)<br>
Real Scale<br>

![Alt text](materials/Q1/output4.png)<br>
Normalized Scale


## Q2

### Consumer preferences of used vs new car when purchasing of EV.
![Alt text](materials/Q2/output1.png)<br>

### Consumer preferences of used vs new car when purchasing of ICE.
![Alt text](materials/Q2/output2.png)<br>

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

From the Autocorrelation and Partial Correlation of all condition, the time series is still non-stationary. The time seies must be stationary before applying it to forecasting method because the mean and variance must be constant over time or series.<br>

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

### Recursive multi-step forecasting
![Alt text](materials/Q3/output22.png)<br>

### XGBOOST
![Alt text](materials/Q3/output23.png)<br>

## Q4

### Best type of EV preferred in the past
![Alt text](materials/Q4/output1.png)
### Best type of ICE preferred in the past
![Alt text](materials/Q4/output2.png)
### Best type of EV preferred for the future
![Alt text](materials/Q4/output3.png)
![Alt text](materials/Q4/output4.png)
### Best type of ICE preferred for the future
![Alt text](materials/Q4/output5.png)
![Alt text](materials/Q4/output6.png)











