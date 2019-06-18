package de.htwg.se.ttfe.controller

import scala.swing.Publisher

trait ControllerInterface extends Publisher {
  def cell(row: Int, col: Int):Int
  def size: Int
  def exit: Unit
  def fieldToString: String
  def moveDirection(direction: String):Unit
  def restart: Unit
  def load: Unit
  def save: Unit

  def getGridById(id: Int): (Int, String)

  def getAllGrids: List[(Int, String)]

  def deleteGridById(id: Int): Boolean

  def saveGrid(grid: String): Unit
}


import scala.swing.event.Event
class Moved extends Event
class Restarted extends Event