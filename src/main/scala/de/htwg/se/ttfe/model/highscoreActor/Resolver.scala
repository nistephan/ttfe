package de.htwg.se.ttfe.model.highscoreActor

import akka.actor._

object Resolver{
  case class Resolve(path: String)
  case class Resolved(actorRef: ActorRef)
  case object Failed
}

class Resolver extends Actor {
  import Resolver._
  var requester: ActorRef = _

def receive = {
  case Resolve(path) => println(" resolve message received " + path)
    context.system.actorSelection(path) ! Identify(path)
    requester = sender

  case ActorIdentity(path, Some(ref)) => println("identity message received" + "\n" + "ActorPath: " + path + " resolved to ActorRef: " + ref)
    requester ! Resolved(ref)

  case ActorIdentity(path, None) => requester ! Failed
  }
}
