name := "scala-st4"

organization := "com.geishatokyo"

version := "4.0.4-SNAPSHOT"

scalaVersion := "2.9.1"

crossScalaVersions := Seq("2.9.0","2.9.0-1","2.9.1","2.9.1-1")

resolvers += Resolver.mavenLocal

publishTo := Some(Resolver.file("localMaven",Path.userHome / ".m2" / "repository"))

libraryDependencies ++= Seq(
  "org.antlr" % "ST4" % "4.0.4",
  "junit" % "junit" % "4.7" % "test"
)

libraryDependencies <+= (scalaVersion) {sv => sv match{
  case "2.9.1" | "2.9.1-1" => "org.specs2" %% "specs2" % "1.8.2" % "test"
  case "2.9.0" | "2.9.0-1" => "org.specs2" %% "specs2" % "1.7.1" % "test"
}}