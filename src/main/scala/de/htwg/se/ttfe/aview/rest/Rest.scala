package de.htwg.se.ttfe.aview.rest

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{Route, StandardRoute}
import akka.pattern.ask
import akka.stream.ActorMaterializer
import akka.util.Timeout
import de.htwg.se.ttfe.controller.ControllerInterface
import de.htwg.se.ttfe.controller.actorBaseImpl.CommandMessage.Command
import de.htwg.se.ttfe.controller.actorBaseImpl.{CommandActor, TurnAsInstance}

import scala.concurrent.Await
import scala.concurrent.duration._

class Rest(controller: ControllerInterface) {
  implicit val timeout = Timeout(5 seconds)
  implicit val system = ActorSystem("system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val turnAsInstance: TurnAsInstance = new TurnAsInstance(controller)

  val cmdActor = system.actorOf(Props(classOf[CommandActor], turnAsInstance.controller), "commandactor")

  val route: Route = get {
    pathSingleSlash {
      complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "<h1>2048</h1>"))
    }
    path("2048") {
      printTui
    } ~
      path("2048" / "load") {
        controller.load
        printTui
      } ~
      path("2048" / "save") {
        controller.save
        printTui
      } ~
      path("2048" / Segment) {
        command => {
          processInputLine(command)
          printTui
        }
      }
  }

  def printTui: StandardRoute = {
    println("Turn made with REST!")

    complete(HttpEntity(ContentTypes.`text/html(UTF-8)`,
      "<h1>2048</h1>" + controller.fieldToString.toString + "\n"))
  }

  val bindingFuture = Http().bindAndHandle(route, "0.0.0.0", 8080)

  def unbind: Unit = {
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }

  def processInputLine(input: String): Unit = {
    input match {
      case "L" => controller.moveDirection("L")
      case "R" => controller.moveDirection("R")
      case "D" => controller.moveDirection("D")
      case "U" => controller.moveDirection("U")
      case _ =>
    }
  }
}
