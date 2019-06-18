package de.htwg.se.ttfe

import com.google.inject.Guice
import de.htwg.se.ttfe.controller.{ControllerInterface, Restarted}
import de.htwg.se.ttfe.aview.Tui
import de.htwg.se.ttfe.aview.gui.SwingGui
import de.htwg.se.ttfe.aview.microservice.{CounterService, CounterServiceServer}
import de.htwg.se.ttfe.aview.rest.Rest
import de.htwg.se.ttfe.model.FileIO

import scala.io.StdIn.readLine

object TTFE {
  val injector = Guice.createInjector(new TTFEModule)
  val controller = injector.getInstance(classOf[ControllerInterface])

  val fileIO = new FileIO
  // val controller = new Controller(fileIO.load)

  val tui = new Tui(controller)
  val rest = new Rest(controller)
  new CounterServiceServer(new CounterService)
  //val gui = new SwingGui(controller)
  controller.publish(new Restarted)


  def main(args: Array[String]): Unit = {
    while (true) {
      tui.processInputLine(readLine())
    }
  }
}