package de.htwg.se.ttfe.model

class Field(sizeI:Int) {
  val size:Int = sizeI
  var amountBlocks:Int = 0
  var grid = Array.ofDim[Cell](size, size)
  for(x <- 0 to (size -1)){
    for(y <- 0 to (size -1)){
      grid(x)(y) = new Cell(0)
    }
  }
  var score:Int = 0
  createRandom()


  def isMovePossible(): Boolean={
    var movePossible:Boolean = false

    if(amountBlocks == (size * size)){
      for(i <- 0 to (size - 1)){
        for(j <- 0 to (size - 1)){
          if(i > 0 && grid(j)(i).value == grid(j)(i - 1).value){
            movePossible = true
          }
          if(j > 0 && grid(j)(i).value == grid(j - 1)(i).value){
            movePossible = true
          }
        }
      }
    }else{
      movePossible = true
    }

    movePossible
  }

  def addCells(y:Int, x:Int, dirX:Int, dirY:Int): Boolean ={
    var moved:Boolean = false
    if(grid(y)(x).value > 0) {
      if (grid(y)(x).value == grid(y + dirY)(x + dirX).value) {
        score += grid(y)(x).value + grid(y + dirY)(x + dirX).value
        grid(y + dirY)(x + dirX).value_=(grid(y)(x).value + grid(y + dirY)(x + dirX).value)
        grid(y)(x).value_=(0)
        moved = true
      }
    }
    moved
  }

  def move(y:Int, x:Int, dirX:Int, dirY:Int): Boolean ={
    var moved:Boolean = false
    if(grid(y)(x).value > 0) {
      if (grid(y + dirY)(x + dirX).value == 0) {
        grid(y + dirY)(x + dirX).value_=(grid(y)(x).value)
        grid(y)(x).value_=(0)
        moved = true
      }
    }
    moved
  }


  def moveDirection(direction:String): Unit = {
    //TODO switch case L,R,U,Ds
    var moved:Boolean = false
    direction match {
      case "R" => moved = moveRight()
      case "L" => moved = moveLeft()
      case "U" => moved = moveUp()
      case "D" => moved = moveDown()
    }
    if(moved){
      createRandom()
    }
    printField()
  }

  def moveRight(): Boolean={
    var moved:Boolean = false
    var movedToCreate:Boolean = false
    for(i <- 0 to (size - 1 + (size / 2))) {
      for (x <- (size - 2) to 0 by -1) {
        for (y <- 0 to (size - 1)) {
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
      for (x <- 1 to (size - 1)) {
        for (y <- 0 to (size - 1)) {
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
      for (y <- 1 to (size - 1)) {
        for (x <- 0 to (size - 1)) {
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
        for (x <- 0 to (size - 1)) {
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

  def printField(): Unit ={
    println("Score: " + score)
    for(i <- 0 to size - 1){
      print("|")
      for(j <- 0 to size - 1){
        if(grid(i)(j) == null){
          print("\t|")
        }else{
          print(grid(i)(j) + "\t|")
        }
      }
      println()
    }
  }

  def createRandom(): Unit ={
    val r = new scala.util.Random()
    var x:Int = 0
    var y:Int = 0
    do {
      x = r.nextInt(size)
      y = r.nextInt(size)
    }while(grid(x)(y).value != 0)

    var value = r.nextInt(2)
    if(value == 0){
      value = 2
    }else{
      value = 4
    }
    grid(x)(y).value_=(value)
  }

  def loose(): Unit ={
    println("No more moves possible. Click R to restart.")
  }

  def restart(): Unit ={
    score = 0
    amountBlocks = 0
    for(x <- 0 to (size -1)){
      for(y <- 0 to (size -1)){
        grid(x)(y) = new Cell(0)
      }
    }
    createRandom()
    println("Restarted:")
    printField()
  }
}