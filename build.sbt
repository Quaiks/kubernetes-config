organization := "com.github.k8.config"

name := "kubernetes-config"

version := "1.0"

scalaVersion := "2.11.8"

scalacOptions ++= Seq("-feature", "-language:postfixOps")

libraryDependencies ++= {
  Seq(
    "com.typesafe" % "config" % "1.3.1",
    "io.fabric8" % "kubernetes-client" % "1.4.27"
  )
}