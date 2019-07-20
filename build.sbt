import sbt.Keys._

val projectVersion = "1.1.0-SNAPSHOT"

val dependencies = Seq(
  "com.typesafe.akka" %% "akka-actor" % Versions.akka,
  "com.typesafe.akka" %% "akka-slf4j" % Versions.akka,
  "com.typesafe.scala-logging" %% "scala-logging" % Versions.scalaLogging,
  "org.yaml" % "snakeyaml" % Versions.snakeYaml,
  "com.jsuereth" %% "scala-arm" % Versions.scalaArm,
  "com.github.tototoshi" %% "scala-csv" % Versions.scalaCsv,
  "org.apache.commons" % "commons-math3" % Versions.commonsMath,
  "com.github.scopt" %% "scopt" % Versions.scopt,
  "org.apache.commons" % "commons-lang3" % Versions.commonsLang3,
  "ch.qos.logback" % "logback-classic" % Versions.logbackClassic,
  "com.typesafe.akka" %% "akka-testkit" % Versions.akka % "test, it",
  "org.scalatest" %% "scalatest" % Versions.scalatest % "test, it",
  "com.h2database" % "h2" % Versions.h2 % "test, it"
)

lazy val root = (project in file(".")).enablePlugins(PackPlugin).settings(
  organization := "eu.semberal",
  name := "dbstress",
  version := projectVersion,
  scalaVersion := Versions.scala,
  scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Xfatal-warnings", "-Ywarn-unused-import")
).settings(resolvers ++= Seq(
  "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
)).configs(IntegrationTest).settings(Defaults.itSettings: _*)
  .settings(libraryDependencies ++= dependencies: _*)
  .settings(packMain := Map("dbstress" -> "eu.semberal.dbstress.Main"))
  .settings(packArchiveExcludes := List("VERSION", "Makefile")
)
