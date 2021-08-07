package com.example.quickstart

object Response {

  final case class Greeting(value: String) extends AnyVal

  final case class Joke(joke: String) extends AnyVal

}
