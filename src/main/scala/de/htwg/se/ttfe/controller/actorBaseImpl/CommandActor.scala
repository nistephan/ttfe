package de.htwg.se.ttfe.controller.actorBaseImpl

import akka.actor._
import de.htwg.se.ttfe.controller.ControllerInterface
import de.htwg.se.ttfe.controller.actorBaseImpl.CommandMessage.Command
import de.htwg.se.ttfe.util.Utils


object CommandMessage {

  final case class Command(cmd: String)

}

class TurnAsInstance(val controller: ControllerInterface) extends AnyVal

class CommandActor(val conf: TurnAsInstance) extends Actor {
  val turn: ControllerInterface = conf.controller

  override def receive: Receive = {
    case Command(command) =>
      command.charAt(0) match {
        case 'a' => sender ! Utils.processAction(turn, "left")
        case 'd' => sender ! Utils.processAction(turn, "right")
        case 's' => sender ! Utils.processAction(turn, "down")
        case 'w' => sender ! Utils.processAction(turn, "up")
        case 'z' => sender ! Utils.processAction(turn, "save")
        case 'u' => sender ! Utils.processAction(turn, "load")
        case 't' => sender ! Utils.processAction(turn, "exit")
        case _ => sender ! Utils.processAction(turn, "blank")
      }
  }
}
