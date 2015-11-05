import play.Project._

name := """test_1"""

version := "1.0-SNAPSHOT"

libraryDependencies += javaJdbc
libraryDependencies += jdbc




libraryDependencies ++= Seq(
	javaEbean,
  "org.webjars" %% "webjars-play" % "2.2.2", 
  "org.webjars" % "bootstrap" % "2.3.1"
)

 

playJavaSettings
