import Dependencies._

lazy val root = (project in file("."))
  .settings(
    organization := "com.example",
    name := "quickstart-extended",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.13.6",
    libraryDependencies ++= Seq(
      Http4s.Dsl,
      Http4s.Circe,
      Http4s.EmberClient,
      Http4s.EmberServer,
      Circe,
      Logback,
      Munit,
      MunitCatsEffect
    ),
    addCompilerPlugin(
      "org.typelevel" %% "kind-projector" % "0.13.0" cross CrossVersion.full
    ),
    addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1"),
    testFrameworks += new TestFramework("munit.Framework")
  )
