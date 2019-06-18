package de.htwg.se.ttfe.model.daoComponent.mongoDBImpl

import de.htwg.se.ttfe.model.daoComponent.DAOInterface

import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase}
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.mongodb.scala.model.Filters._


import scala.concurrent.Await
import scala.concurrent.duration.Duration

class GridDAO extends DAOInterface {
  // Create Codec. Codec is used to convert case class to BSON
  val codecRegistry = fromRegistries(fromProviders(classOf[Grid]), DEFAULT_CODEC_REGISTRY )

  val mongoClient: MongoClient = MongoClient()
  val database: MongoDatabase = mongoClient.getDatabase("grids").withCodecRegistry(codecRegistry)
  val collection: MongoCollection[Grid] = database.getCollection("grid")

  var inc = 0

  override def getGridById(id: Int): (Int, String) = {
    Await.result(collection.find(equal("_id", id)).first().toFuture(),Duration.Inf).get()
  }

  override def getAllGrids: List[(Int, String)] = {
    Await.result(collection.find().toFuture(),Duration.Inf).toList.map(grid => grid.get())
  }

  override def saveGrid(grid: String): Unit = {
    inc += 1
    Await.result(collection.insertOne(Grid(inc, grid)).toFuture(), Duration.Inf)
  }

  override def deleteGridById(id: Int): Boolean = {
    Await.result(collection.deleteOne(equal("_id", id)).toFuture(),Duration.Inf).wasAcknowledged()
  }
}


case class Grid(_id: Int, grid: String) {
  def get(): (Int, String) = (_id, grid)
}
