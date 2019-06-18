package de.htwg.se.ttfe.aview.microservice

import akka.actor.ActorSystem
import akka.http.javadsl.server.directives.RouteDirectives
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{Route, StandardRoute}
import akka.stream.ActorMaterializer

import scala.concurrent.Future

class CounterServiceServer(counter: CounterService) {
  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val route: Route = get {
    pathSingleSlash {
      complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "<h1>SimpleCounter</h1>"))
    }
    path("") {
      parseHtml
    } ~
      path("inc") {
        counter.inc
        parseHtml
      } ~
      path("dec") {
        counter.dec
        parseHtml
      }
  }

  def parseHtml: StandardRoute = {
    complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, counter.toHtml))
  }

  val bindingFuture: Future[Http.ServerBinding] = Http().bindAndHandle(route, "0.0.0.0", 8081)

  def unbind(): Unit = {
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}