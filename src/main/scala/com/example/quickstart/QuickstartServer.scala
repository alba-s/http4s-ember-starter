package com.example.quickstart

import cats.effect.{Async, ExitCode}
import cats.implicits._
import com.comcast.ip4s._
import org.http4s.ember.client.EmberClientBuilder
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.implicits._

object QuickstartServer {

  def stream[F[_]: Async]: F[ExitCode] = {
    val server = for {
      client <- EmberClientBuilder.default[F].build
      jokes = Jokes[F](client)
      httpApp = Routes.helloWorld[F](HelloWorld[F]) <+> Routes.joke[F](
        jokes
      ) <+> Routes.wsJokes[F](jokes)
      server <-
        EmberServerBuilder
          .default[F]
          .withHost(ipv4"0.0.0.0")
          .withPort(port"8080")
          .withHttpApp(httpApp.orNotFound)
          .build
    } yield server

    server.use(_ => Async[F].never.as(ExitCode.Success))
  }
}
