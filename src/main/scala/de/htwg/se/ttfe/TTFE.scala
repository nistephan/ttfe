package de.htwg.se.ttfe

import de.htwg.se.ttfe.model.Field


object TTFE {
  def main(args: Array[String]): Unit = {
    val fieldSize = 4
    val field = new Field(fieldSize)
    field.printField()

    while (true) {
      val input = scala.io.StdIn.readLine
      input match {
        case "w" => field.moveDirection("U")
        case "a" => field.moveDirection("L")
        case "s" => field.moveDirection("D")
        case "d" => field.moveDirection("R")
        case "r" => field.restart()
        case "e" =>
          print("exiting\n")
          sys.exit
        case _ => print("False Input!\n")
      }
    }
  }
}