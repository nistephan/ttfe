package de.htwg.se.ttfe.controller

import scala.swing.Publisher

trait ControllerInterface extends Publisher {
  def exit: Unit
  def fieldToString: String
  def moveDirection(direction: String):Unit
  def restart(): Unit
}


import scala.swing.event.Event
class Moved extends Event
class Restarted extends Event