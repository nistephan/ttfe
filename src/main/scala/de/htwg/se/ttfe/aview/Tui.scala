package de.htwg.se.ttfe.aview

import de.htwg.se.ttfe.controller.Controller
import de.htwg.se.ttfe.util.Observer

class Tui (controller: Controller) extends Observer{
  controller.add(this)

  def processInputLine(input: String):Unit = {
    input match {
      case "w" => controller.moveDirection("U")
      case "a" => controller.moveDirection("L")
      case "s" => controller.moveDirection("D")
      case "d" => controller.moveDirection("R")
      case "r" => controller.restart()
      case "e" =>
        print("exiting\n")
        sys.exit
      case _ => print("False Input!\n")
    }
  }

  override def update: Unit =  print(controller.fieldToString + "\n")

}
