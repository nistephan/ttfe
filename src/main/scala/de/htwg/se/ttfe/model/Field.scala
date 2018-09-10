package de.htwg.se.ttfe.model

class Field(sizeI:Int) {
  val size:Int = sizeI
  var amountBlocks:Int = 0
  var blocks = Array.ofDim[Block](size, size)
  var score:Int = 0

  createRandom()

  def createBlock(xI:Int, yI:Int, valueI:Int): Unit ={
    blocks(yI)(xI) = new Block(valueI)
    amountBlocks += 1
  }

  def isMovePossible(): Boolean={
    var movePossible:Boolean = false

    if(amountBlocks == (size * size)){
      for(i <- 0 to (size - 1)){
        for(j <- 0 to (size - 1)){
          if(i > 0 && blocks(j)(i).value == blocks(j)(i - 1).value){
            movePossible = true
          }
          if(j > 0 && blocks(j)(i).value == blocks(j - 1)(i).value){
            movePossible = true
          }
        }
      }
    }else{
      movePossible = true
    }

    return movePossible
  }

  def moveRight(): Unit ={
    println("Right")
    if(isMovePossible()) {
      var moved: Boolean = false
      moved = moveLoopRight()

      for (y <- 0 to size - 1) {
        for (x <- (size - 2) to 0 by -1) {
          if (blocks(y)(x) != null && blocks(y)(x + 1) != null) {
            if (blocks(y)(x).value == blocks(y)(x + 1).value) {
              score += blocks(y)(x + 1).double()
              blocks(y)(x) = null
              moved = true
              amountBlocks -= 1
            }
          }
        }
      }
      if (!moved) {
        moved = moveLoopRight()
      } else {
        moveLoopRight()
      }

      if (moved) {
        createRandom()
      }
      printField()
    }else{
      loose()
    }
  }

  def moveLoopRight(): Boolean ={
    var moved:Boolean = false
    for(y <- 0 to size - 1) {
      for (i <- 0 to (size - 1)) {
        for (x <- (size - 2) to 0 by -1) {
          //move right
          if (blocks(y)(x) != null && blocks(y)(x + 1) == null) {
            blocks(y)(x + 1) = blocks(y)(x)
            blocks(y)(x) = null
            moved = true
          }
        }
      }
    }
    return moved
  }


  def moveLeft(): Unit ={
    println("Left")
    if(isMovePossible()) {
      var moved: Boolean = false
      moved = moveLoopLeft()

      for (y <- 0 to size - 1) {
        for (x <- 1 to (size - 1)) {
          if (blocks(y)(x) != null && blocks(y)(x - 1) != null) {
            if (blocks(y)(x).value == blocks(y)(x - 1).value) {
              score += blocks(y)(x - 1).double()
              blocks(y)(x) = null
              moved = true
              amountBlocks -= 1
            }
          }
        }
      }

      if (!moved) {
        moved = moveLoopLeft()
      } else {
        moveLoopLeft()
      }

      if (moved) {
        createRandom()
      }
      printField()
    }else{
      loose()
    }
  }

  def moveLoopLeft(): Boolean ={
    var moved:Boolean = false
    for(y <- 0 to size - 1) {
      for (i <- 0 to (size - 1)) {
        for (x <- 1 to (size - 1)) {
          //move left
          if (blocks(y)(x) != null && blocks(y)(x - 1) == null) {
            blocks(y)(x - 1) = blocks(y)(x)
            blocks(y)(x) = null
            moved = true
          }
        }
      }
    }
    return moved
  }


  def moveUp(): Unit ={
    println("Up")
    if(isMovePossible()) {
      var moved: Boolean = false
      moved = moveLoopUp()

      for (y <- 1 to size - 1) {
        for (x <- 0 to (size - 1)) {
          if (blocks(y)(x) != null && blocks(y - 1)(x) != null) {
            if (blocks(y)(x).value == blocks(y - 1)(x).value) {
              score += blocks(y - 1)(x).double()
              blocks(y)(x) = null
              moved = true
              amountBlocks -= 1
            }
          }
        }
      }
      if (!moved) {
        moved = moveLoopUp()
      } else {
        moveLoopUp()
      }

      if (moved) {
        createRandom()
      }
      printField()
    }else{
      loose()
    }
  }

  def moveLoopUp(): Boolean ={
    var moved:Boolean = false
    for (x <- 0 to (size - 1)) {
      for (i <- 0 to (size - 1)) {
        for(y <- 1 to (size - 1)) {
          //move up
          if (blocks(y)(x) != null && blocks(y - 1)(x) == null) {
            blocks(y - 1)(x) = blocks(y)(x)
            blocks(y)(x) = null
            moved = true
          }
        }
      }
    }
    return moved
  }

  def moveDown(): Unit ={
    println("Down")
    if(isMovePossible()) {
      var moved: Boolean = false
      moved = moveLoopDown()

      for (y <- (size - 2) to 0 by -1) {
        for (x <- 0 to (size - 1)) {
          if (blocks(y)(x) != null && blocks(y + 1)(x) != null) {
            if (blocks(y)(x).value == blocks(y + 1)(x).value) {
              score += blocks(y + 1)(x).double()
              blocks(y)(x) = null
              moved = true
              amountBlocks -= 1
            }
          }
        }
      }
      if (!moved) {
        moved = moveLoopDown()
      } else {
        moveLoopDown()
      }

      if (moved) {
        createRandom()
      }
      printField()
    }else{
      loose()
    }
  }

  def moveLoopDown(): Boolean ={
    var moved:Boolean = false
    for (x <- 0 to (size - 1)) {
      for (i <- 0 to (size - 1)) {
        for(y <- (size - 2) to 0 by -1) {
          //move up
          if (blocks(y)(x) != null && blocks(y + 1)(x) == null) {
            blocks(y + 1)(x) = blocks(y)(x)
            blocks(y)(x) = null
            moved = true
          }
        }
      }
    }
    return moved
  }

  def printField(): Unit ={
    println("Score: " + score)
    for(i <- 0 to size - 1){
      print("|")
      for(j <- 0 to size - 1){
        if(blocks(i)(j) == null){
          print("\t|")
        }else{
          print(blocks(i)(j) + "\t|")
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
      x = r.nextInt(4)
      y = r.nextInt(4)
    }while(blocks(y)(x) != null)

    var value = r.nextInt(2)
    if(value == 0){
      value = 2
    }else{
      value = 4
    }
    createBlock(x, y, value)
  }

  def loose(): Unit ={
    println("No more moves possible. Click R to restart.")
  }

  def restart(): Unit ={
    blocks = Array.ofDim[Block](size, size)
    createRandom()
    println("Restarted:")
    printField()
  }
}