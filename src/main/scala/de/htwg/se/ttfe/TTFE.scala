package de.htwg.se.ttfe

import de.htwg.se.ttfe.controller.Controller
import de.htwg.se.ttfe.aview.Tui
import de.htwg.se.ttfe.model.Field

import scala.io.StdIn.readLine

object TTFE {
    val defaultFieldSize = 4
    val controller = new Controller(new Field(defaultFieldSize))
    val tui = new Tui(controller)
    controller.notifyObservers

    def main(args: Array[String]): Unit = {
      var input: String = ""
      while (true) {
        input = readLine()
        tui.processInputLine(input)
      }
    }
}