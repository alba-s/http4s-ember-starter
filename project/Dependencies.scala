import sbt._

object Dependencies {
  object Http4s {
    val Dsl = "org.http4s" %% "http4s-dsl" % "0.23.0"
    val Circe = "org.http4s" %% "http4s-circe" % Dsl.revision
    val EmberClient = "org.http4s" %% "http4s-ember-client" % Dsl.revision
    val EmberServer = "org.http4s" %% "http4s-ember-server" % Dsl.revision
  }

  val Circe = "io.circe" %% "circe-generic" % "0.14.1"

  val Logback = "ch.qos.logback" % "logback-classic" % "1.2.5"

  val Munit = "org.scalameta" %% "munit" % "0.7.27" % Test
  val MunitCatsEffect =
    "org.typelevel" %% "munit-cats-effect-3" % "1.0.5" % Test
}
