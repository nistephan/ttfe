package de.htwg.se.ttfe.model

class Field(size:Int) {
  val value_four = 4
  val grid: Array[Array[Int]] = Array.ofDim[Int](size, size)

  var score:Int = 0
  createRandom()


  def isMovePossible(x: Int, y: Int): Boolean= {
    /*var movePossible:Boolean = false
    for(x <- 0 until size){
      for(y <- 0 until size){
        if(x > 0 && grid(y)(x) == grid(y)(x - 1)){
          movePossible = true
        }
        if(y > 0 && grid(y)(x) == grid(y - 1)(x)){
          movePossible = true
        }
      }
    }
    movePossible*/

    if(x == size){
      println("false")
      return false
    }
    println("X: " + x + ", Y: " + y + ", Value: " + grid(x)(y))
    if(grid(x)(y) == 0 || (x > 0 && grid(y)(x) == grid(y)(x - 1)) || (y > 0 && grid(y)(x) == grid(y - 1)(x))){
      println("true")
      return true
    }

    if (y < size - 1) {
      if(isMovePossible(x, y + 1)){
        return true
      }
    }

    if(isMovePossible(x + 1, 0)){
      return true
    }else{
      return false
    }
  }

  def moveValidation(x: Int, y: Int): Boolean ={
    println("X: " + x + ", Y: " + y + ", Value: " + grid(x)(y))
    if(grid(x)(y) == 0 || (x > 0 && grid(y)(x) == grid(y)(x - 1)) || (y > 0 && grid(y)(x) == grid(y - 1)(x))){
      true
    }else {
      false
    }
  }

  def addCells(y:Int, x:Int, dirX:Int, dirY:Int): Boolean ={
    if(grid(y)(x) > 0) {
      if (grid(y)(x) == grid(y + dirY)(x + dirX)) {
        score += grid(y)(x) + grid(y + dirY)(x + dirX)
        grid(y + dirY)(x + dirX) = (grid(y)(x) + grid(y + dirY)(x + dirX))
        grid(y)(x) = 0
        true
      }else{
        false
      }
    } else{
      false
    }
  }

  def move(y:Int, x:Int, dirX:Int, dirY:Int): Boolean ={
    if(grid(y)(x) > 0) {
      if (grid(y + dirY)(x + dirX) == 0) {
        grid(y + dirY)(x + dirX) = (grid(y)(x))
        grid(y)(x) = 0
        true
      }else{
        false
      }
    }else{
      false
    }
  }


  def moveDirection(direction:String): Unit = {
    var moved:Boolean = false
    direction match {
      case "R" => moved = moveRight()
      case "L" => moved = moveLeft()
      case "U" => moved = moveUp()
      case "D" => moved = moveDown()
    }
    if(moved){
      createRandom()
      if(!isMovePossible(0,0)){
        loose()
      }
    }
  }

  def moveRight(): Boolean={
    var moved:Boolean = false
    var movedToCreate:Boolean = false
    for(i <- 0 to (size - 1 + (size / 2))) {
      for (x <- (size - 2) to 0 by -1) {
        for (y <- 0 until size) {
          if(i != (size / 2)) {
            moved = move(y, x, 1, 0)
          }
          if(i == (size / 2)) {
            moved = addCells(y, x, 1, 0)
          }
          if (moved) {
            movedToCreate = true
          }
        }
      }
    }
    movedToCreate
  }

  def moveLeft(): Boolean ={
    var moved:Boolean = false
    var movedToCreate:Boolean = false
    for(i <- 0 to (size - 1 + (size / 2))) {
      for (x <- 1 until size) {
        for (y <- 0 until size) {
          if(i != (size / 2)){
            moved = move(y, x, -1, 0)
          }
          if(i == (size / 2)) {
            moved = addCells(y, x, -1, 0)
          }
          if(moved){
            movedToCreate = true
          }
        }
      }
    }
    movedToCreate
  }

  def moveUp(): Boolean ={
    var moved:Boolean = false
    var movedToCreate:Boolean = false
    for(i <- 0 to (size - 1 + (size / 2))) {
      for (y <- 1 until size) {
        for (x <- 0 until size) {
          if(i != (size / 2)) {
            moved = move(y, x, 0, -1)
          }
          if(i == (size / 2)) {
            moved = addCells(y, x, 0, -1)
          }
          if(moved){
            movedToCreate = true
          }
        }
      }
    }
    movedToCreate
  }

  def moveDown(): Boolean ={
    var moved:Boolean = false
    var movedToCreate:Boolean = false
    for(i <- 0 to (size - 1 + (size / 2))) {
      for (y <- (size - 2) to 0 by -1) {
        for (x <- 0 until size) {
          if(i != (size / 2)) {
            moved = move(y, x, 0, 1)
          }
          if(i == (size / 2)) {
            moved = addCells(y, x, 0, 1)
          }
          if(moved){
            movedToCreate = true
          }
        }
      }
    }
    movedToCreate
  }

  override def toString: String ={
    var fieldString: String = "Score: " + score + "\n"
    for(i <- 0 until size){
      fieldString += "|"
      for(j <- 0 until size){
        fieldString += (grid(i)(j) + "\t|")
      }
      fieldString += "\n"
    }

    fieldString
  }

  def getScore(scores : Array[Int]): Int ={
    scores.sum
  }

  def createRandom(): Unit ={
    val r = new scala.util.Random()
    var x:Int = 0
    var y:Int = 0
    do {
      x = r.nextInt(size)
      y = r.nextInt(size)
    }while(grid(x)(y) != 0)

    var value = r.nextInt(2)
    if(value == 0){
      value = 2
    }else{
      value = value_four
    }
    grid(x)(y)=(value)
  }

  def loose(): Unit ={
    print("No more moves possible. Click R to restart.\n")
  }

  def restart(): Unit ={
    score = 0
    for(x <- 0 until size){
      for(y <- 0 until size){
        grid(x)(y) = 0
      }
    }
    createRandom()
    print("Restarted:\n")
  }
}