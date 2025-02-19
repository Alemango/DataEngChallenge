package com.kueski.movingaverage.io

import com.typesafe.config.ConfigFactory
import com.kueski.movingaverage.utils.{LoggingTrait}

object Config extends LoggingTrait {
  private val config = try {
    ConfigFactory.load()
    } catch {
      case e: Exception =>
        log("ERROR", s"Falló la lectura del archivo de configuración: ${e.getMessage}")
        throw e
    }

  def get(key: String): String = config.getString(key)

  def getInt(key: String, default: Int): Int = {
    if (config.hasPath(key)) config.getInt(key) else default
  }
    
}
