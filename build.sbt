import Dependencies._

ThisBuild / scalaVersion := "2.13.3"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "zio-sample",
    libraryDependencies ++= dependencies,
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )

lazy val dependencies =
  Cats.all ++
  Http4s.all ++
  Config.all ++
  Streaming.all ++
  ZIO.all ++
  Tapir.all ++
  Doobie.all ++
  Enum.all ++
  STTP.all ++
  Circe.all ++
  Logging.all ++
  Flyway.all ++
  Testing.all

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
