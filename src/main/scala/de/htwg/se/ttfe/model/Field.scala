package de.htwg.se.ttfe.model

class Field(size:Int) {
  val value_four = 4
  val grid: Array[Array[Int]] = Array.ofDim[Int](size, size)

  var score:Int = 0
  createRandom()


  def isMovePossible(x: Int, y: Int): Boolean= {
    if(x == size){
      return false
    }
    if(grid(x)(y) == 0 || (x > 0 && grid(y)(x) == grid(y)(x - 1)) || (y > 0 && grid(y)(x) == grid(y - 1)(x))){
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
    if(grid(x)(y) == 0 || (x > 0 && grid(y)(x) == grid(y)(x - 1)) || (y > 0 && grid(y)(x) == grid(y - 1)(x))){
      true
    }else {
      false
    }
  }

  def addCells(y:Int, x:Int, dirX:Int, dirY:Int): Boolean ={
    if(grid(y)(x) > 0) {
      if (grid(y)(x) == grid(y + dirY)(x + dirX)) {
        //TODO score
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
    direction match {
      case "R" => createRandom2(moveRight)
      case "L" => createRandom2(moveLeft)
      case "U" => createRandom2(moveUp)
      case "D" => createRandom2(moveDown)
    }
  }

  //TODO change name
  def createRandom2(moved:Boolean): Unit = {
    if(moved){
      createRandom()
      if(!isMovePossible(0, 0)){
        loose()
      }
    }
  }

  def moveRight() : Boolean = {
    def loop(i:Int, x:Int, y:Int, moved:Boolean): Boolean={
      if(i == (size - 1 + (size / 2))){
        moved
      }else{
        if(y < size){
          if(i != (size / 2)) {
              if(move(y, x, 1, 0)){
                loop(i, x, y + 1, true)
              }else{
                loop(i, x, y + 1, moved)
              }
          }else if(i == (size / 2)) {
            if(addCells(y, x, 1, 0)){
              loop(i, x, y + 1, true)
            }else{
              loop(i, x, y + 1, moved)
            }
          }else{
            loop(i, x, y + 1, moved)
          }
        }else if(x > 0){
          loop(i, x - 1, 0, moved)
        }else{
          loop(i + 1, (size - 2), 0, moved)
        }
      }
    }
    loop(0, (size - 2), 0, false)
  }

  def moveLeft() : Boolean = {
    def loop(i:Int, x:Int, y:Int, moved:Boolean): Boolean={
      if(i == (size - 1 + (size / 2))){
        moved
      }else{
        if(y < size){
          if(i != (size / 2)) {
            if(move(y, x, -1, 0)){
              loop(i, x, y + 1, true)
            }else{
              loop(i, x, y + 1, moved)
            }
          }else if(i == (size / 2)) {
            if(addCells(y, x, -1, 0)){
              loop(i, x, y + 1, true)
            }else{
              loop(i, x, y + 1, moved)
            }
          }else{
            loop(i, x, y + 1, moved)
          }
        }else if(x < size - 1){
          loop(i, x + 1, 0, moved)
        }else{
          loop(i + 1, 1, 0, moved)
        }
      }
    }
    loop(0, 1, 0, false)
  }

  def moveUp() : Boolean = {
    def loop(i:Int, x:Int, y:Int, moved:Boolean): Boolean={
      if(i == (size - 1 + (size / 2))){
        moved
      }else{
        if(y < size){
          if(i != (size / 2)) {
            if(move(y, x, 0, -1)){
              loop(i, x, y + 1, true)
            }else{
              loop(i, x, y + 1, moved)
            }
          }else if(i == (size / 2)) {
            if(addCells(y, x, 0, -1)){
              loop(i, x, y + 1, true)
            }else{
              loop(i, x, y + 1, moved)
            }
          }else{
            loop(i, x, y + 1, moved)
          }
        }else if(x < size - 1){
          loop(i, x + 1, 1, moved)
        }else{
          loop(i + 1, 0, 1, moved)
        }
      }
    }
    loop(0, 0, 1, false)
  }

  def moveDown() : Boolean = {
    def loop(i:Int, x:Int, y:Int, moved:Boolean): Boolean={
      if(i == (size - 1 + (size / 2))){
        moved
      }else{
        if(x < size){
          if(i != (size / 2)) {
            if(move(y, x, 0, 1)){
              loop(i, x + 1, y, true)
            }else{
              loop(i, x + 1, y, moved)
            }
          }else if(i == (size / 2)) {
            if(addCells(y, x, 0, 1)){
              loop(i, x + 1, y, true)
            }else{
              loop(i, x + 1, y, moved)
            }
          }else{
            loop(i, x + 1, y, moved)
          }
        }else if(y > 0){
          loop(i, 0, y - 1, moved)
        }else{
          loop(i + 1, 0, (size - 2), moved)
        }
      }
    }
    loop(0, 0, (size - 2), false)
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



  def createRandom(): Unit ={
    val r = new scala.util.Random()
    var x:Int = 0
    var y:Int = 0
    do {
      x = r.nextInt(size)
      y = r.nextInt(size)
    }while(grid(r.nextInt(size))(r.nextInt(size)) != 0)

    val value = r.nextInt(2)
    if(value == 0){
      grid(x)(y) = 2
    }else{
      grid(x)(y) = value_four
    }
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