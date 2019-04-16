package de.htwg.se.ttfe

import de.htwg.se.ttfe.controller.Controller
import de.htwg.se.ttfe.aview.Tui
import de.htwg.se.ttfe.model.{Field, FileIO, Matrix}
import play.api.libs.json.{JsValue, Json}

import scala.io.Source._
import scala.io.StdIn.readLine

object TTFE {
    val fileIO = new FileIO
    val defaultFieldSize = 4
    val controller = new Controller(fileIO.load)
    controller.field = controller.field.start
    val tui = new Tui(controller)
    controller.notifyObservers


    def main(args: Array[String]): Unit = {
      while (true) {
        tui.processInputLine(readLine())
      }
    }
}