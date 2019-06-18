package de.htwg.se.ttfe.model.daoComponent

trait DAOInterface {
  def getGridById(id: Int): (Int, String)
  def getAllGrids: List[(Int, String)]

  def saveGrid(grid: String): Unit

  def deleteGridById(id: Int): Boolean
}
