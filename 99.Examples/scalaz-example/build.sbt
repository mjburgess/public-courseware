
scalaVersion := "2.12.1"

val scalazVersion = "7.2.14"

libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.14"


initialCommands in console := "import scalaz._; import Scalaz._"
