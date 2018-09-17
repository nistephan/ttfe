package de.htwg.se.ttfe

import de.htwg.se.ttfe.model.Player
import de.htwg.se.ttfe.model.Cell

import de.htwg.se.ttfe.model.Field


object Main {
  def main(args: Array[String]): Unit = {
    val student = Player("bla1")
    println("Hello, " + student.name)

    val field = new Field(4)
    field.printField()

    while (true) {
      val input = readLine()
      input match {
        case "w" => field.moveDirection("U")
        case "a" => field.moveDirection("L")
        case "s" => field.moveDirection("D")
        case "d" => field.moveDirection("R")
        case "r" => field.restart()
        case "e" => {
          println("exiting")
          sys.exit
        }
        case _ => println("False Input!!!")
      }
    }
  }
}