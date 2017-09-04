lazy val root = (project in file(".")).settings(
	scalaVersion := "2.11.8",

	organization := "qa",

	version := "1.0-DEV",

	libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0",
	libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0"
)

logLevel in run := Level.Warn
