package de.htwg.se.ttfe.model.fieldComponent.FieldMockImpl

import de.htwg.se.ttfe.model.fieldComponent.FieldInterface
import de.htwg.se.ttfe.model.fieldComponent.fieldBaseImpl.Matrix
import play.api.libs.json.JsValue

class Field (var size:Int, var score: Int) extends FieldInterface{
  override def cellsField: Matrix[Integer] = null

  override def start(): FieldInterface = this

  override def isMovePossible(x: Int, y: Int, movePossible: Boolean): Boolean = false

  override def addCells(y: Int, x: Int, dirX: Int, dirY: Int, field: FieldInterface): FieldInterface = this

  override def move(y: Int, x: Int, dirX: Int, dirY: Int, field: FieldInterface): FieldInterface = this

  override def moveDirection(direction: String): FieldInterface = this

  override def createRandom2(field: FieldInterface): FieldInterface = this

  override def moveRight(): FieldInterface = this

  override def moveLeft(): FieldInterface = this

  override def moveUp(): FieldInterface = this

  override def moveDown(): FieldInterface = this

  override def createRandom(field: FieldInterface, fieldOld: FieldInterface): FieldInterface = this

  override def createRandomHelper(field: FieldInterface): FieldInterface = this

  override def loose(): Unit = this

  override def toStringHelper(i: Int, j: Int, fieldString: String): String = ""

  override def toJson: JsValue = null
}
