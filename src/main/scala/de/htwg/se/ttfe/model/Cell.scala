package de.htwg.se.ttfe.model

class Cell(valueI:Int) {
  private[this] var _value: Int = valueI

  def value: Int = _value

  def value_=(value: Int): Unit = {
    _value = value
  }

  override def toString: String = s"$value"

}