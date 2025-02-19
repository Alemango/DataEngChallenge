package com.kueski.movingaverage.io

import com.typesafe.config.ConfigFactory

object Config {
  private val config = ConfigFactory.load()

  def get(key: String): String = config.getString(key)

  def getInt(key: String, default: Int): Int = {
    if (config.hasPath(key)) config.getInt(key) else default
  }
}
