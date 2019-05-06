package de.htwg.se.ttfe.model.fieldComponent

import play.api.libs.json.JsValue

trait FieldInterface {
  def start(): Field
  def isMovePossible(x: Int, y: Int, movePossible: Boolean): Boolean
  def addCells(y:Int, x:Int, dirX:Int, dirY:Int, field: Field): Field
  def move(y:Int, x:Int, dirX:Int, dirY:Int, field: Field): Field
  def moveDirection(direction:String): Field
  def createRandom2(field: Field): Field
  def moveRight() : Field
  def moveLeft() : Field
  def moveUp() : Field
  def moveDown() : Field
  def createRandom(field: Field, fieldOld: Field): Field
  def createRandomHelper(field: Field): Field
  def loose(): Unit
  override def toString: String
  def toStringHelper(i:Int, j: Int, fieldString: String): String
  def toJson:JsValue
}
