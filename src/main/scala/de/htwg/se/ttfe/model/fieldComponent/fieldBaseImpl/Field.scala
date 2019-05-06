package de.htwg.se.ttfe.model.fieldComponent.fieldBaseImpl

import de.htwg.se.ttfe.model.fieldComponent.FieldInterface
import play.api.libs.json.{JsNumber, JsValue, Json}

case class Field (cells: Matrix[Integer]) extends FieldInterface {
  def this(size: Int) = this(new Matrix[Integer](size, 0))
  val value_four = 4
  val sizeI = cells.size
  var score:Int = 0

  def size: Int={
    sizeI
  }

  def start(): FieldInterface ={
    createRandom(this, this)
  }

  def cellsField: Matrix[Integer]={
    cells
  }

  def isMovePossible(x: Int, y: Int, movePossible: Boolean): Boolean ={
    if(x == size){
      movePossible
    }else{
      if (y < size - 1) {
        if(cells.cell(x, y) == 0 || (x > 0 && cells.cell(y, x) == cells.cell(y, x - 1)) || (y > 0 && cells.cell(y, x) == cells.cell(y - 1, x))){
          isMovePossible(x, y + 1, true)
        }else{
          isMovePossible(x, y + 1, movePossible)
        }
      }else{
        isMovePossible(x + 1, 0, movePossible)
      }
    }
  }

  def addCells(y:Int, x:Int, dirX:Int, dirY:Int, field: FieldInterface): FieldInterface ={
    if(field.cellsField.cell(y, x) > 0) {
      if (field.cellsField.cell(y, x) == field.cellsField.cell(y + dirY, x + dirX)) {
        //TODO score
        score += field.cellsField.cell(y, x) + field.cellsField.cell(y + dirY, x + dirX)
        val newField = copy(field.cellsField.replaceCell(y + dirY, x + dirX, field.cellsField.cell(y, x) + field.cellsField.cell(y + dirY, x + dirX)).replaceCell(y, x, 0))
        newField
      }else{
        field
      }
    } else{
      field
    }
  }

  def move(y:Int, x:Int, dirX:Int, dirY:Int, field: FieldInterface): FieldInterface ={
    if(field.cellsField.cell(y, x) > 0) {
      if (field.cellsField.cell(y + dirY, x + dirX) == 0) {
        val newField = copy(field.cellsField.replaceCell(y + dirY, x + dirX, field.cellsField.cell(y, x)).replaceCell(y, x, 0))
        newField
      }else{
        field
      }
    }else{
      field
    }
  }

  def moveDirection(direction:String): FieldInterface = {
    direction match {
      case "R" => createRandom2(moveRight)
      case "L" => createRandom2(moveLeft)
      case "U" => createRandom2(moveUp)
      case "D" => createRandom2(moveDown)
    }
  }

  def createRandom2(field: FieldInterface): FieldInterface = {
    if(field.cellsField != this.cells){ //successful move
      createRandom(field, field)
      /*if(!isMovePossible(0, 0, false)){
        loose()
      }*/
    }else{
      field
    }
  }

  def moveRight() : FieldInterface = {
    def loop(i:Int, x:Int, y:Int, moved:Boolean, field: FieldInterface): FieldInterface={
      if(i == (size - 1 + (size / 2))){
          field
      }else{
        if(y < size){
          if(i != (size / 2)) {
              val tmpField = move(y, x, 1, 0, field)
              if(tmpField.cellsField != this.cells){
                loop(i, x, y + 1, true, tmpField)
              }else{
                loop(i, x, y + 1, moved, field)
              }
          }else if(i == (size / 2)) {
            val tmpField2 = addCells(y, x, 1, 0, field)
            if(tmpField2.cellsField != this.cells){
              loop(i, x, y + 1, true, tmpField2)
            }else{
              loop(i, x, y + 1, moved, field)
            }
          }else{
            loop(i, x, y + 1, moved, field)
          }
        }else if(x > 0){
          loop(i, x - 1, 0, moved, field)
        }else{
          loop(i + 1, (size - 2), 0, moved, field)
        }
      }
    }
    loop(0, (size - 2), 0, false, this)
  }

  def moveLeft() : FieldInterface = {
    def loop(i:Int, x:Int, y:Int, moved:Boolean, field: FieldInterface): FieldInterface={
      if(i == (size - 1 + (size / 2))){
        field
      }else{
        if(y < size){
          if(i != (size / 2)) {
            val tmpField = move(y, x, -1, 0, field)
            if(tmpField.cellsField != this.cells){
              loop(i, x, y + 1, true, tmpField)
            }else{
              loop(i, x, y + 1, moved, field)
            }
          }else if(i == (size / 2)) {
            val tmpField2 = addCells(y, x, -1, 0, field)
            if(tmpField2.cellsField != this.cells){
              loop(i, x, y + 1, true, tmpField2)
            }else{
              loop(i, x, y + 1, moved, field)
            }
          }else{
            loop(i, x, y + 1, moved, field)
          }
        }else if(x < size - 1){
          loop(i, x + 1, 0, moved, field)
        }else{
          loop(i + 1, 1, 0, moved, field)
        }
      }
    }
    loop(0, 1, 0, false, this)
  }

  def moveUp() : FieldInterface = {
    def loop(i:Int, x:Int, y:Int, moved:Boolean, field: FieldInterface): FieldInterface={
      if(i == (size - 1 + (size / 2))){
        field
      }else{
        if(y < size){
          if(i != (size / 2)) {
            val tmpField = move(y, x, 0, -1, field)
            if(tmpField.cellsField != this.cells){
              loop(i, x, y + 1, true, tmpField)
            }else{
              loop(i, x, y + 1, moved, field)
            }
          }else if(i == (size / 2)) {
            val tmpField2 = addCells(y, x, 0, -1, field)
            if(tmpField2.cellsField != this.cells){
              loop(i, x, y + 1, true, tmpField2)
            }else{
              loop(i, x, y + 1, moved, field)
            }
          }else{
            loop(i, x, y + 1, moved, field)
          }
        }else if(x < size - 1){
          loop(i, x + 1, 1, moved, field)
        }else{
          loop(i + 1, 0, 1, moved, field)
        }
      }
    }
    loop(0, 0, 1, false, this)
  }

  def moveDown() : FieldInterface = {
    def loop(i:Int, x:Int, y:Int, moved:Boolean, field: FieldInterface): FieldInterface={
      if(i == (size - 1 + (size / 2))){
        field
      }else{
        if(x < size){
          if(i != (size / 2)) {
            val tmpField = move(y, x, 0, 1, field)
            if(tmpField.cellsField != this.cells){
              loop(i, x + 1, y, true, tmpField)
            }else{
              loop(i, x + 1, y, moved, field)
            }
          }else if(i == (size / 2)) {
            val tmpField2 = addCells(y, x, 0, 1, field)
            if(tmpField2.cellsField != this.cells){
              loop(i, x + 1, y, true, tmpField2)
            }else{
              loop(i, x + 1, y, moved, field)
            }
          }else{
            loop(i, x + 1, y, moved, field)
          }
        }else if(y > 0){
          loop(i, 0, y - 1, moved, field)
        }else{
          loop(i + 1, 0, (size - 2), moved, field)
        }
      }
    }
    loop(0, 0, (size - 2), false, this)
  }

  def createRandom(field: FieldInterface, fieldOld: FieldInterface): FieldInterface ={
    if(field.cellsField != fieldOld.cellsField){
      field
    }else{
      createRandom(createRandomHelper(field), fieldOld)
    }
  }

  def createRandomHelper(field: FieldInterface): FieldInterface ={
    val r = new scala.util.Random()
    val x: Int = r.nextInt(size)
    val y: Int = r.nextInt(size)
    if(field.cellsField.cell(x, y) != 0){
      field
    }else{
      val value = r.nextInt(2)
      if(value == 0){
        copy(field.cellsField.replaceCell(x, y, 2))
      }else{
        copy(field.cellsField.replaceCell(x, y, value_four))
      }
    }
  }

  def loose(): Unit ={
    print("No more moves possible. Click R to restart.\n")
  }

  /*def restart(): Field ={
    score = 0
    def loop(x:Int, y:Int): Unit={
      if(x != 4){
        copy(cells.replaceCell(x, y, 0))
        //cells.cell(x, y) = 0
        if(y < size - 1){
          loop(x, y + 1)
        }
        loop(x + 1, 0)
      }
    }
    loop(0, 0)
    createRandom()
    print("Restarted:\n")
  }*/

  override def toString: String ={
    toStringHelper(0, 0, "Score: " + score + "\n|")
  }

  def toStringHelper(i:Int, j: Int, fieldString: String): String={
    if(i == size){
      fieldString
    }else {
      if (j < size) {
        toStringHelper(i, j + 1, fieldString + (cells.cell(i, j) + "\t|"))
      }else{
        if(i == (size - 1) && j == (size)){
          toStringHelper(i + 1, 0, fieldString + "\n")
        }else{
          toStringHelper(i + 1, 0, fieldString + "\n" + "|" )
        }
      }
    }
  }

  def toJson:JsValue = {
    Json.obj("size" -> JsNumber(size),
      "cells" -> Json.toJson(
        for {row <- 0 until size;
             col <- 0 until size} yield {
          Json.obj(
            "row" -> row,
            "col" -> col,
            "cell" -> JsNumber(BigDecimal(cells.cell(row, col))))
        }
      )
    )
  }

}
