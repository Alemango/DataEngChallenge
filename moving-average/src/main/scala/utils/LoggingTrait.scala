package com.kueski.movingaverage.utils

trait LoggingTrait {
  def log(alert: String, message: String): Unit = {
    val timestamp = java.time.LocalDateTime.now()
    println(s"[$timestamp] [$alert]: $message")
  }
}
