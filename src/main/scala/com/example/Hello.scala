package com.example

import java.io.File

import com.typesafe.config.ConfigFactory
import play.api.libs.ws._
import play.api.libs.ws.ning._

import scala.concurrent.{ExecutionContext, Await}

import scala.concurrent.duration.Duration

object Hello {
  def main(args: Array[String]): Unit = {


    val configuration = play.api.Configuration(ConfigFactory.parseFile(new File("/Users/redarqas/projects/resto-app/certs/ws.conf")))

    val env = play.api.Environment.simple(play.api.Mode.Prod)
    val parser = new DefaultWSConfigParser(configuration, env)
    val builder = new NingAsyncHttpClientConfigBuilder(parser.parse())
    val secureClient : WSClient = new NingWSClient(builder.build())
    val response = secureClient.url("https://example.com:8443/restaurants").get()
    val result = Await.result(response, Duration.Inf)

    println(s" =======> Hello, world! ${result.body}")
    sys.exit(0)
  }
}
