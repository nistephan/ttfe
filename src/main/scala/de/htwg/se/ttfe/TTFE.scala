package de.htwg.se.ttfe

import de.htwg.se.ttfe.controller.{Controller, Restarted}
import de.htwg.se.ttfe.aview.Tui
import de.htwg.se.ttfe.aview.gui.SwingGui
import de.htwg.se.ttfe.model.FileIO
import scala.io.StdIn.readLine

object TTFE {
    val fileIO = new FileIO
    val defaultFieldSize = 4
    val controller = new Controller(fileIO.load)
    controller.field = controller.field.start
    val tui = new Tui(controller)
    val gui = new SwingGui(controller)
    controller.publish(new Restarted)


    def main(args: Array[String]): Unit = {
      while (true) {
        tui.processInputLine(readLine())
      }
    }
}