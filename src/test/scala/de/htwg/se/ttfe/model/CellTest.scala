package de.htwg.se.ttfe.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CellTest extends WordSpec with Matchers {
  "A Cell" when { "new" should {
    val value = 4
    val cell = new Cell(value)
    "have a value"  in {
      cell.value should be(value)
    }
    "have a nice String representation" in {
      cell.toString should be(value.toString)
    }
  }}
  "A Cell" when { "set value" should{
    var value = 2
    val cell = new Cell(value)
    value *= 2
    cell.value_=(value)
    "have this value" in {
      cell.value should be(value)
    }
  }}
}