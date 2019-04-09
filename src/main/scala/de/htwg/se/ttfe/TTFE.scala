package de.htwg.se.ttfe

import de.htwg.se.ttfe.controller.Controller
import de.htwg.se.ttfe.aview.Tui
import de.htwg.se.ttfe.model.{Field, Matrix}

import scala.io.StdIn.readLine

object TTFE {
    val defaultFieldSize = 4
    println("controller started")
    //val matrix = new Matrix[Integer](defaultFieldSize, 0)
    val controller = new Controller(new Field(new Matrix[Integer](defaultFieldSize, 2)))
  println("controller started")
    val tui = new Tui(controller)
    controller.notifyObservers
    println("controller started")

    def main(args: Array[String]): Unit = {
      while (true) {
        tui.processInputLine(readLine())
      }
    }
}