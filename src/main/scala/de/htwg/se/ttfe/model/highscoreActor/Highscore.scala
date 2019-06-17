package de.htwg.se.ttfe.model.highscoreActor
import scala.io.Source
import akka.actor._

class Highscore extends Actor {
  val path = "akka://Main/scala/de.htwg.se.ttfe/model/highscoreActor/Highscore"
  val resolver = context.actorOf(Props[Resolver], "resolver")

  resolver ! Resolver.Resolve(path)

  def receive = {
    case "test" => println("received test")
    case Resolver.Resolved(actorRef) => println("ActorRef received!")
    case Resolver.Failed => println("failed to resolve ActorPath: " + path)
//      context.stop(self)
    case "save" => println("xd")//save score

    case "load" => println("xd")//load list of scores
    case _ => println("received unknown message")
  }

}
