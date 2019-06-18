package de.htwg.se.ttfe.aview.microservice

import scala.io.StdIn

object CounterServiceMain {
  def main(args: Array[String]): Unit = {
    val webserver = new CounterServiceServer(new CounterService)

    println("Server online at http://localhost:8081/")
    println("Press RETURN to stop...")

    //StdIn.readLine() // let it run until user presses return

    while(true) {
      println("running: counter") // fix for docker
    }

    webserver.unbind()
  }
}

class CounterService {
  var counter = 0

  def inc(): Unit = {
    counter += 1
  }

  def dec(): Unit = {
    counter -= 1
  }

  override def toString: String = counter.toString

  def toHtml: String = {
    "<p>" + toString + "</p>"
  }
}