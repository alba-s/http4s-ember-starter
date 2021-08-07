package com.example.quickstart

import cats.effect.Concurrent
import cats.implicits._
import com.example.quickstart.Codecs._
import org.http4s.Method._
import org.http4s.circe.CirceEntityCodec._
import org.http4s.client.Client
import org.http4s.client.dsl.Http4sClientDsl
import org.http4s.implicits._

trait Jokes[F[_]] {
  def get: F[Response.Joke]
}

object Jokes {

  def apply[F[_]: Concurrent](C: Client[F]): Jokes[F] =
    new Jokes[F] {
      val dsl: Http4sClientDsl[F] = new Http4sClientDsl[F] {}
      import dsl._

      def get: F[Response.Joke] = {
        C.expect[Response.Joke](GET(uri"https://icanhazdadjoke.com/"))
          .adaptError(JokeError(_))
      }
    }

  final case class JokeError(e: Throwable) extends RuntimeException

}
