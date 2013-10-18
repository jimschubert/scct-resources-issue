import sbt._
import sbt.Keys._

object ScctResourcesIssueBuild extends Build {
  val appSettings = Seq(
    name := "SCCT Resources Issue",
    organization := "com.ipreferjim",
    version := "0.1-SNAPSHOT",
    scalaVersion := "2.10.2",
    libraryDependencies ++= Seq(
      Dependencies.typesafeConfig,
      Dependencies.mysql,
      Dependencies.scalatest
    )
  )

  val scctSettings = ScctPlugin.instrumentSettings ++ Seq(
    // resourceDirectory in ScctPlugin.ScctTest <<= (resourceDirectory in Test), // this setting is optional
    unmanagedResources in ScctPlugin.ScctTest <<= (unmanagedResources in Test)
  )

  /**
   * ISSUE: Toggle the last two settings lines below and run ./sbt scct:test
   */
  lazy val scctResourcesIssue = Project("scct-resources-issue", file("."))
    .settings(Project.defaultSettings:_*)
    .settings(appSettings:_*)
    // .settings(ScctPlugin.instrumentSettings: _*) // Does not work with test resources
    .settings(scctSettings:_*) // works with test resources
}

object Dependencies {
  val typesafeConfig = "com.typesafe" % "config" % "1.0.2"
  val mysql = "mysql" % "mysql-connector-java" % "5.1.6"
  val scalatest = "org.scalatest" %% "scalatest" % "1.9.1"
}
