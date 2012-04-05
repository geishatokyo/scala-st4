name := "scala-st4"

organization := "com.geishatokyo"

version := "4.0.4-SNAPSHOT"

scalaVersion := "2.9.1"

crossScalaVersions := Seq("2.9.0","2.9.0-1","2.9.1","2.9.1-1")

resolvers += Resolver.mavenLocal

publishTo := Some(Resolver.file("localMaven",Path.userHome / ".m2" / "repository"))

libraryDependencies ++= Seq(
  "org.antlr" % "ST4" % "4.0.4",
  "org.specs2" %% "specs2" % "1.8.2" % "test",
  "junit" % "junit" % "4.7" % "test"
)