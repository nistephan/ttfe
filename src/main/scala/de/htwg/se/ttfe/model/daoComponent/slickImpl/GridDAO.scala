package de.htwg.se.ttfe.model.daoComponent.slickImpl

import de.htwg.se.ttfe.model.daoComponent.DAOInterface
import slick.jdbc.H2Profile.api._
import slick.lifted.ProvenShape

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

class GridDAO extends DAOInterface {
  private val grids = TableQuery[Grids]
  val db = Database.forConfig("h2mem1")
  db.run(DBIO.seq(grids.schema.create))

  override def getGridById(id: Int): (Int, String) = {
    val query = db.run(grids.filter(_.id === id).result.headOption)
    Await.result(query, Duration.Inf).get
  }

  override def getAllGrids: List[(Int, String)] = {
    val query = db.run(grids.result)
    Await.result(query,Duration.Inf).toList
  }

  override def saveGrid(grid: String): Unit = {
    db.run(grids += (0, grid))
  }

  override def deleteGridById(id: Int): Boolean = {
    val query = db.run(grids.filter(_.id === id).delete) map { _ > 0}
    Await.result(query, Duration.Inf)
  }
}

case class Grids(tag: Tag) extends Table[(Int, String)](tag, "Grids") {
  def id: Rep[Int] = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def grid: Rep[String] = column[String]("Grid")

  def * : ProvenShape[(Int, String)] = (id,grid)
}