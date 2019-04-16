package de.htwg.se.ttfe.controller

import java.io.{File, PrintWriter}

import de.htwg.se.ttfe.model.Field
import de.htwg.se.ttfe.util.Observable

class Controller(var field:Field) extends Observable{

  /*def createNewField(size:Int):Unit = {
    field = new Field(size)
    notifyObservers
  }*/

  def exit: Unit = {
    val pw = new PrintWriter(new File("src/main/resources/grid.json" ))
    pw.write(field.toJson.toString())
    pw.close
    print("exiting\n")
    sys.exit
  }

  def fieldToString: String = field.toString

  def moveDirection(direction: String):Unit = {
    field = field.moveDirection(direction)
    notifyObservers
  }

  def restart(): Unit = {
    //field.restart()
    notifyObservers
  }

}
