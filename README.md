# Kueski Data Challenge

## Objective
The objective of this challenge is to assess your skills using distributed programming.

## Problem
The challenge consists in computing in a distributed way the 7-day moving average for historical stock prices using either Spark or MapReduce (in the Appendix below we provide a definition of a moving average).

You will use the stock prices data contained here:
https://www.kaggle.com/ehallmar/daily-historical-stock-prices-1970-2018#historical_stock_price s.csv (you need to register/log in in order to download the data file).

You have to compute the 7-day moving average for at least two stocks, using all the available information for those stocks. There is not a unique way to do this, and you are free to implement the solution you find the best.

## Appendix
The N-day moving average of a stock prices time series is defined as follows. Suppose we have our daily (close time) stock prices represented in a vector [p_1, p_2, …, p_M], where M is the number of prices. Then, the N-day moving averages of this series is another series defined by:

[
(p_1 + p_2 + … + p_N) / N,
(p_2 + p_3 + … + p_{N + 1}) / N,
…,
(p_{M - N + 1}, p_{M - N + 2}, …, p_M) / N
],
(where N <= M).

That is, we take “windows” of N-days periods, and average our data along these windows,
producing a series consisting of averages on these windows.
