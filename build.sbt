name := """skybaps"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  "org.webjars" % "jquery" % "1.11.3",
  "org.webjars" % "bootstrap" % "3.3.5",
  "org.webjars" % "font-awesome" % "4.4.0",
  "org.webjars" % "html5shiv" % "3.7.3",
  "org.webjars" % "respond" % "1.4.2"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
