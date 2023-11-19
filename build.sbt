ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

libraryDependencies += "com.outr" % "hasher_2.13" % "1.2.2"

lazy val root = (project in file("."))
  .settings(
    name := "funpar-project"
  )
