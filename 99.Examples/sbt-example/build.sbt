scalaVersion := "2.11.8"

organization := "qa"

version := "1.0-DEV"

val commonSettings = Seq(
	scalaVersion := "2.11.7",
	organization := "QA",
	version := "1.0-SNAPSHOT",	
	libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0",
	libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" 

 )

 
 
lazy val blog_lib = (project in file("blog-lib")).
 settings(commonSettings)

lazy val diary_app = (project in file("diary-app")) . dependsOn("blog_lib").
 settings(commonSettings)

mainClass in diary_app := Some("Main")


lazy val root = (project in file(".")).settings(commonSettings).aggregate(blog_lib, diary_app)

/*
 lazy val common = (project in file("common")).
 settings(commonSettings)

 lazy val backend = (project in file("backend")).
 settings(commonSettings).
 dependsOn("common")

*/

resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" 

logBuffered in Test := false
