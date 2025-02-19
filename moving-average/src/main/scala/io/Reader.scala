package com.kueski.movingaverage.io

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.to_date
import com.kueski.movingaverage.utils.{LoggingTrait}

class DataLoader extends DataLoaderInterface with LoggingTrait {

  override def loadStockData(spark: SparkSession, filePath: String): DataFrame = {
    try {
      log("INFO", s"Intentando cargar datos desde: $filePath")

      val df = spark.read
        .option("header", "true")
        .option("inferSchema", "true")
        .csv(filePath)

      log("INFO", "Datos cargados correctamente, validando estructura...")

      validateData(df)

      log("INFO", "Datos validados exitosamente.")

      df
    } catch {
      case e: Exception =>
        log("ERROR", s"Falló la carga y validación de datos: ${e.getMessage}")
        throw e
    }
  }
}
