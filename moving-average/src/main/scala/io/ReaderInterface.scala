package com.kueski.movingaverage.io

import org.apache.spark.sql.{DataFrame, SparkSession}

trait DataLoaderInterface {
    def loadStockData(spark: SparkSession, filePath: String): DataFrame

    def validateData(df: DataFrame): Unit = {
        if (df.isEmpty) {
            throw new IllegalArgumentException("Error: El Dataframe está vacío")
        }
    }
}