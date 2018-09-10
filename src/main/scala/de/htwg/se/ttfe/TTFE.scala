package de.htwg.se.ttfe

import de.htwg.se.ttfe.model.Player
import de.htwg.se.ttfe.model.Block

import de.htwg.se.ttfe.model.Field


object Main {
  def main(args: Array[String]): Unit = {
    val student = Player("bla")
    println("Hello, " + student.name)

    val field = new Field(4)
    field.printField()

    while (true) {
      val input = readLine()
      input match {
        case "w" => field.moveUp()
        case "a" => field.moveLeft()
        case "s" => field.moveDown()
        case "d" => field.moveRight()
        case "r" => field.restart() //TODO reset points
        case "e" => {
          println("exiting")
          sys.exit
        }
        case _ => println("False Input!!!")
      }
    }
  }
}