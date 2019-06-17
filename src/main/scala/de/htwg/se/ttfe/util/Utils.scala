package de.htwg.se.ttfe.util

import de.htwg.se.ttfe.controller.ControllerInterface

object Utils {
  def processAction(controller: ControllerInterface, action: String): Unit = {
    action match {
      case "left" => controller.moveDirection("L")
      case "right" => controller.moveDirection("R")
      case "down" => controller.moveDirection("D")
      case "up" => controller.moveDirection("U")
      case "reset" => print("reset")
      case "save" => controller.save
      case "load" => controller.load
      case "exit" => controller.exit
      case _ => print("Invalid move\n")
    }
  }
}
