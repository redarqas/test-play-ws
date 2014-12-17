package com.example

import com.typesafe.config.ConfigFactory
import play.api.libs.ws._
import play.api.libs.ws.ning._

import scala.concurrent.{ExecutionContext, Await}
import ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object Hello {
  def main(args: Array[String]): Unit = {

    val configuration = play.api.Configuration(ConfigFactory.parseString(
      """
      |ws.ssl {
        trustManager = {
        |    stores = [
        |      { type = "JKS", path = "/Users/redarqas/projects/activator-play-tls-example/certs/exampletrust.jks" }
        |    ]
        |  }
      |}
      """.stripMargin))
    val env = play.api.Environment.simple(play.api.Mode.Dev)
    val parser = new DefaultWSConfigParser(configuration, env)
    val builder = new NingAsyncHttpClientConfigBuilder(parser.parse())
    val secureClient : WSClient = new NingWSClient(builder.build())
    val response = secureClient.url("https://www.example.com:8443/restaurants").get()
    val s = response.map(r => r.status)
    val result = Await.result(s, Duration.Inf)

    println(s" =======> Hello, world! $result")
  }
}
