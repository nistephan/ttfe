package de.htwg.se.ttfe.aview

import de.htwg.se.ttfe.controller.{Controller, ControllerInterface, Moved, Restarted}
import de.htwg.se.ttfe.util.Observer

import scala.swing.Reactor

class Tui (controller: ControllerInterface) extends Reactor{

  listenTo(controller)
  //controller.add(this)

  def processInputLine(input: String):Unit = {
    input match {
      case "w" => controller.moveDirection("U")
      case "a" => controller.moveDirection("L")
      case "s" => controller.moveDirection("D")
      case "d" => controller.moveDirection("R")
      case "r" => controller.restart
      case "e" => controller.exit
      case _ => print("False Input!\n")
    }
  }

  reactions += {
    case event: Moved => printTui
    case event: Restarted     => printTui
  }

  def printTui: Unit = {
    println(controller.fieldToString)
  }

}
