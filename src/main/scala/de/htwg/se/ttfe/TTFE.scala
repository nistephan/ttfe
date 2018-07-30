package de.htwg.se.ttfe

import de.htwg.se.ttfe.model.Player

object TTFE {
  def main(args: Array[String]): Unit = {
    val student = Player("Your Name")
    println("Hello, " + student.name)
  }
}
