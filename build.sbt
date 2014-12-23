name := """test-play-ws"""

version := "1.0"

scalaVersion := "2.11.1"

resolvers ++= Seq(
  "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository",
  "Novapost repo" at "http://nibbler:9081/nexus/content/repositories/nova",
  "Novapost snaps repo" at "http://nibbler:9081/nexus/content/repositories/snapshots",
  "rediscala" at "https://raw.github.com/etaty/rediscala-mvn/master/releases/"
)

libraryDependencies ++= List(
  "org.slf4j" % "slf4j-api" % "1.7.7",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "ch.qos.logback" % "logback-core" % "1.1.2",
  "com.typesafe.play" %% "play-ws" % "2.4.0-M2",
  "org.apache.httpcomponents" % "httpclient" % "4.3.6",
  "org.apache.httpcomponents" % "httpclient-cache" % "4.3.6",
  "org.apache.httpcomponents" % "httpmime" % "4.3.6",
  "org.apache.httpcomponents" % "httpcore" % "4.3.6",
  "org.apache.httpcomponents" % "fluent-hc" % "4.3.6",
  "commons-logging" % "commons-logging" % "1.1.3",
  "org.scalatest" %% "scalatest" % "2.1.6" % "test"
)
