package de.htwg.se.ttfe.model

class Block(valueI:Int) {
  var value:Int = valueI

  def double(): Int ={
    value *= 2
    return value
  }

  def getValue(): Int={
    return value
  }

  override def toString: String = s"$value"


}