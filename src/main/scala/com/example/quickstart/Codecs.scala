package com.example.quickstart

import com.example.quickstart.Response.{Greeting, Joke}
import io.circe.{Codec, Encoder}
import io.circe.generic.semiauto.{deriveCodec, deriveEncoder}

object Codecs {

  implicit val greetingEncoder: Encoder[Greeting] = deriveEncoder

  implicit val jokeCodec: Codec[Joke] = deriveCodec

}
