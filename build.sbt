name := "scala-st4"

organization := "com.geishatokyo"

version := "4.0.4-SNAPSHOT"

scalaVersion := "2.9.1"

crossScalaVersions := Seq("2.9.0", "2.9.0-1", "2.9.1", "2.9.1-1")

libraryDependencies ++= Seq(
  "org.antlr" % "ST4" % "4.0.4",
  "junit" % "junit" % "4.7" % "test"
)

libraryDependencies <+= (scalaVersion) {sv => sv match{
  case "2.9.1" | "2.9.1-1" => "org.specs2" %% "specs2" % "1.8.2" % "test"
  case "2.9.0" | "2.9.0-1" => "org.specs2" %% "specs2" % "1.7.1" % "test"
}}

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { x => false }

//publishTo <<= version { v: String =>
//  if (v.trim.endsWith("SNAPSHOT"))
//    Some("sonatype-nexus-snapshots" at "https://oss.sonatype.org/content/repositories/snapshots")
//  else
//    Some("sonatype-nexus-staging" at "https://oss.sonatype.org/service/local/staging/deploy/maven2")
//}

pomExtra := 
    <url>https://github.com/geishatokyo/scala-st4</url>
    <licenses>
       <license>
            <name>BSD license</name>
            <url>http://antlr.org/license.html</url>
            <distribution>repo</distribution>
        </license>
    </licenses>
    <scm>
        <connection>scm:git:git://github.com/geishatokyo/scala-st4.git</connection>
        <developerConnection>scm:git:git@github.com/geishatokyo/scala-st4.git</developerConnection>
        <url>scm:git:git://github.com/geishatokyo/scala-st4.git</url>
    </scm>
    <issueManagement>
        <system>github</system>
        <url>https://github.com/geishatokyo/scala-st4/issues</url>
    </issueManagement>
    <developers>
        <developer>
            <id>takezoux2</id>
            <name>Takeshita, Yoshiteru</name>
            <email>takeshita@geishatokyo.com</email>
        </developer>
    </developers>

// vim:set ft=scala :
