val scalaVersionUsed = "2.12.17"

ThisBuild / scalaVersion := scalaVersionUsed

lazy val root = (project in file("."))
  .settings(
    name := "StockMovingAvg",
    version := "0.1.0",
    scalaVersion := scalaVersionUsed,
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-core" % "3.3.0",
      "org.apache.spark" %% "spark-sql" % "3.3.0",
      "com.typesafe" % "config" % "1.4.1"
    )
  )
