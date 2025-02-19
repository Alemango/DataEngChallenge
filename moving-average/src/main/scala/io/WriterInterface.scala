package com.kueski.movingaverage.io

import org.apache.spark.sql.{DataFrame, SparkSession}

trait WriterInterface {
    def saveAsCSV(spark: SparkSession, df: DataFrame, path: String, singleFile: Boolean = false): Unit
    def saveAsParquet(spark: SparkSession, df: DataFrame, path: String, partition: String): Unit
}