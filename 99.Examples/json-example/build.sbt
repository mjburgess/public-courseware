
val json4sNative = "org.json4s" %% "json4s-native" % "latest.integration"

lazy val root = (project in file(".")).settings(            // default project, current folder
        name := "hello",
        version := "1.0",
	libraryDependencies += json4sNative
)

scalaVersion := "2.11.8"                            // principle (default) version



