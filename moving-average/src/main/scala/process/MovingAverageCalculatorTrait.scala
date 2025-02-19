package com.kueski.movingaverage.process

import org.apache.spark.sql.DataFrame

trait MovingAverageCalculatorTrait {

  def validateData(df: DataFrame): Unit = {
    if (df.isEmpty) {
      throw new IllegalArgumentException("Error: El DataFrame está vacío. No se puede calcular el promedio móvil.")
    }
  }

  def computeMovingAverage(df: DataFrame): DataFrame
}
