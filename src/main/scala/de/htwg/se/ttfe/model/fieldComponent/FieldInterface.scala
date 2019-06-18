package de.htwg.se.ttfe.model.fieldComponent

import de.htwg.se.ttfe.model.fieldComponent.fieldBaseImpl.Matrix
import play.api.libs.json.JsValue

trait FieldInterface {
  def cellsField: Matrix[Integer]
  def score: Int
  def size: Int
  def start(): FieldInterface
  def isMovePossible(x: Int, y: Int, movePossible: Boolean): Boolean
  def addCells(y:Int, x:Int, dirX:Int, dirY:Int, field: FieldInterface): FieldInterface
  def move(y:Int, x:Int, dirX:Int, dirY:Int, field: FieldInterface): FieldInterface
  def moveDirection(direction:String): FieldInterface
  def createRandom2(field: FieldInterface): FieldInterface
  def moveRight() : FieldInterface
  def moveLeft() : FieldInterface
  def moveUp() : FieldInterface
  def moveDown() : FieldInterface
  def createRandom(field: FieldInterface, fieldOld: FieldInterface): FieldInterface
  def createRandomHelper(field: FieldInterface): FieldInterface
  def loose(): Unit
  override def toString: String
  def toStringHelper(i:Int, j: Int, fieldString: String): String
  def toJson:JsValue
}
