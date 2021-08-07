package com.example.quickstart

import cats.Applicative
import cats.implicits._

trait HelloWorld[F[_]] {
  def hello(n: Requests.Name): F[Response.Greeting]
}

object HelloWorld {
  def apply[F[_]: Applicative]: HelloWorld[F] = {
    case Requests.Name(name) => Response.Greeting(s"Hello, $name").pure[F]
  }
}
