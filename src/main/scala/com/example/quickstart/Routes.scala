package com.example.quickstart

import cats.effect.{Async, Sync}
import cats.implicits._
import com.example.quickstart.Codecs._
import org.http4s.HttpRoutes
import org.http4s.circe.CirceEntityEncoder._
import org.http4s.dsl.Http4sDsl
import org.http4s.server.websocket.WebSocketBuilder
import org.http4s.websocket.WebSocketFrame

import scala.concurrent.duration.DurationInt

object Routes {

  def joke[F[_]: Sync](service: Jokes[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import dsl._

    HttpRoutes.of[F] {
      case GET -> Root / "joke" =>
        for {
          joke <- service.get
          resp <- Ok(joke)
        } yield resp
    }
  }

  def helloWorld[F[_]: Sync](service: HelloWorld[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import dsl._

    HttpRoutes.of[F] {
      case GET -> Root / "hello" / name =>
        for {
          greeting <- service.hello(Requests.Name(name))
          resp <- Ok(greeting)
        } yield resp
    }
  }

  def wsJokes[F[_]: Async](jokes: Jokes[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import dsl._

    HttpRoutes
      .of[F] {
        case GET -> Root / "ws-jokes" =>
          val send =
            fs2.Stream
              .awakeEvery(3.second)
              .evalMap(_ => jokes.get.map(j => WebSocketFrame.Text(j.joke)))
          WebSocketBuilder[F].build(send, _.void)

      }
  }
}
