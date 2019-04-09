package de.htwg.se.ttfe.model


//TODO case class, Vector, copy,
case class Field(cells: Matrix[Integer]) {
  //def this(size: Int) = this(new Matrix[Integer](size, 0))
  val value_four = 4
  val size = cells.size

  //val grid: Array[Array[Int]] = Array.ofDim[Int](size, size)
  var score:Int = 0
  //
  // createRandom()

  //def set(row: Int, col: Int, value: Int): Grid = copy(cells.replaceCell(row, col, Cell(value)))

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

  def addCells(y:Int, x:Int, dirX:Int, dirY:Int): Field ={
    if(cells.cell(y, x) > 0) {
      if (cells.cell(y, x) == cells.cell(y + dirY, x + dirX)) {
        //TODO score
        score += cells.cell(y, x) + cells.cell(y + dirY, x + dirX)
        val newField = copy(cells.replaceCell(y + dirY, x + dirX, cells.cell(y, x) + cells.cell(y + dirY, x + dirX)).replaceCell(y, x, 0))
       // val newField1 = copy(newField.cells.replaceCell(y, x, 0))
        newField
        //cells.cell(y + dirY, x + dirX) = cells.cell(y, x) + cells.cell(y + dirY, x + dirX)
        //cells.cell(y, x) = 0
        //true
      }else{
        this
      }
    } else{
      this
    }
  }

  def move(y:Int, x:Int, dirX:Int, dirY:Int): Field ={
    if(cells.cell(y, x) > 0) {
      if (cells.cell(y + dirY, x + dirX) == 0) {
        val newField = copy(cells.replaceCell(y + dirY, x + dirX, cells.cell(y, x)).replaceCell(y, x, 0))
       // val newField1 = copy(newField.cells.replaceCell(y, x , 0))
        newField
        //cells.cell(y + dirY, x + dirX) = (cells.cell(y, x))
        //cells.cell(y, x) = 0
        //true
      }else{
        this
      }
    }else{
      this
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

  def createRandom2(moved:Boolean): Unit = {
    if(moved){
      //createRandom()
      if(!isMovePossible(0, 0, false)){
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
              if(move(y, x, 1, 0) != this){
                loop(i, x, y + 1, true)
              }else{
                loop(i, x, y + 1, moved)
              }
          }else if(i == (size / 2)) {
            if(addCells(y, x, 1, 0) != this){
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
            if(move(y, x, -1, 0) != this){
              loop(i, x, y + 1, true)
            }else{
              loop(i, x, y + 1, moved)
            }
          }else if(i == (size / 2)) {
            if(addCells(y, x, -1, 0) != this){
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
            if(move(y, x, 0, -1) != this){
              loop(i, x, y + 1, true)
            }else{
              loop(i, x, y + 1, moved)
            }
          }else if(i == (size / 2)) {
            if(addCells(y, x, 0, -1) != this){
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
            if(move(y, x, 0, 1) != this){
              loop(i, x + 1, y, true)
            }else{
              loop(i, x + 1, y, moved)
            }
          }else if(i == (size / 2)) {
            if(addCells(y, x, 0, 1) != this){
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

  def createRandom(): Unit ={
    while(createRandomHelper().cells == this.cells){
    }
  }

  def createRandomHelper(): Field ={
    val r = new scala.util.Random()
    val x: Int = r.nextInt(size)
    val y: Int = r.nextInt(size)
    if(cells.cell(x, y) != 0){
      this
    }else{
      val value = r.nextInt(2)
      if(value == 0){
        copy(cells.replaceCell(x, y, 2))
        //cells.cell(x, y) = 2
      }else{
        copy(cells.replaceCell(x, y, value_four))
        //cells.cell(x, y) = value_four
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
}