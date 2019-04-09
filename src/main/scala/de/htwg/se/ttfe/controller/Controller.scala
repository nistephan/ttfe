package de.htwg.se.ttfe.controller

import de.htwg.se.ttfe.model.Field
import de.htwg.se.ttfe.util.Observable

class Controller(var field:Field) extends Observable{

  /*def createNewField(size:Int):Unit = {
    field = new Field(size)
    notifyObservers
  }*/

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
