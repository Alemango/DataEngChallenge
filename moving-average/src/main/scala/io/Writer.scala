package com.kueski.movingaverage.io

import org.apache.spark.sql.{DataFrame, SparkSession}
import com.kueski.movingaverage.utils.{LoggingTrait}

class Writer extends WriterInterface with LoggingTrait {

  override def saveAsCSV(spark: SparkSession, df: DataFrame, path: String, singleFile: Boolean = false): Unit = {
    try {
        val dfToSave = if (singleFile) df.coalesce(1) else df
        dfToSave.write
        .option("header", "true")
        .mode("overwrite")
        .csv(path)
        log("INFO", s"CSV guardado en: $path")
    } catch {
        case e: Exception =>
            log("ERROR", s"Falló la escritura de datos CSV: ${e.getMessage}")
            throw e
    }
  }

  override def saveAsParquet(spark: SparkSession, df: DataFrame, path: String, partition: String): Unit = {
    try {
        df.write
        .mode("overwrite")
        .partitionBy(partition)
        .parquet(path)
        log("INFO", s"Parquet guardado en: $path")
    } catch {
        case e: Exception =>
            log("ERROR", s"Falló la escritura de datos PARQUET: ${e.getMessage}")
            throw e
    }
  }
}
