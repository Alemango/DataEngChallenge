package com.kueski.movingaverage.process

import org.apache.spark.sql.{DataFrame}
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, avg}
import com.kueski.movingaverage.utils.LoggingTrait

class MovingAverageCalculator(windowSize: Int) extends MovingAverageCalculatorTrait with LoggingTrait {

    override def computeMovingAverage(df: DataFrame): DataFrame = {
        try {
            log("INFO", "Validando los datos antes de calcular el promedio móvil...")

            validateData(df)

            log("INFO", s"Datos validados. Calculando promedio móvil con ventana de $windowSize días.")

            val windowSpec = Window.partitionBy("ticker").orderBy("format_date").rowsBetween(-windowSize + 1, 0)
            val resultDF = df.select(col("*"), avg(col("close")).over(windowSpec).as("moving_avg"))

            log("INFO", "Cálculo del promedio móvil completado correctamente.")
            resultDF
        } catch {
            case e: Exception =>
                log("ERROR", s"Falló el cálculo del promedio móvil: ${e.getMessage}")
                throw e
        }
    }
}
