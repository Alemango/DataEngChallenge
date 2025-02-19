package com.kueski.movingaverage

import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.functions.{col, to_date}
import org.apache.spark.sql.expressions.Window
import scala.util.{Try, Success, Failure}
import com.kueski.movingaverage.io.{DataLoader, Config, Writer}
import com.kueski.movingaverage.process.{MovingAverageCalculator}

object MovingAverageApp {
  def main(args: Array[String]): Unit = {
    
    val spark: SparkSession = SparkSession.builder()
      .appName("Stock Moving Average")
      .master("local[*]")
      .getOrCreate()

    val input_path: String = Config.get("config.input_file_path")
    val output_path: String = Config.get("config.output_file_path")
    val window_size: Int = Config.getInt("config.window_size", 7)

    val reader: DataLoader = new DataLoader()
    val writer: Writer = new Writer()
    val mov_avg: MovingAverageCalculator = new MovingAverageCalculator(window_size)

    val stockData = reader.loadStockData(spark, input_path)
                          .select(col("ticker"), col("close"), to_date(col("date"), "yyyy-MM-dd").as("format_date"))
                          .where(col("ticker").isin("AAPL", "MSFT"))

    val result = mov_avg.computeMovingAverage(stockData)

    writer.saveAsParquet(spark, result, output_path, "ticker")
  }
}
