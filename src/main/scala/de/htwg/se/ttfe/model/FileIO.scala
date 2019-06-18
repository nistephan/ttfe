package de.htwg.se.ttfe.model

import java.io.{File, PrintWriter}

import com.google.inject.Guice
import play.api.libs.json._
import de.htwg.se.ttfe.TTFEModule
import de.htwg.se.ttfe.model.fieldComponent.FieldInterface
import de.htwg.se.ttfe.model.fieldComponent.fieldBaseImpl.{Field, Matrix}

import scala.io.Source
import scala.util.{Failure, Success, Try}

class FileIO extends FileIOInterface {

  def load: Option[FieldInterface] = {
    val data =
      Try(Source.fromFile("src/main/resources/grid.json").getLines.mkString) match {
      case Success(v) =>
        val json: JsValue = Json.parse(v)
        val size = (json \ "size").get.toString.toInt
        val score = (json \ "score").get.toString.toInt
        def loop(index: Int, matrix: Matrix[Integer]): Matrix[Integer] ={
          if(index != size * size){
            val row = (json \\ "row") (index).as[Int]
            val col = (json \\ "col") (index).as[Int]
            val cell = (json \\ "cell")(index).as[Int]
            loop(index + 1, matrix.replaceCell(row, col, cell))
          }else{
            matrix
          }
        }
        val matrix = loop(0, new Matrix[Integer](size, 0))
        Some(new Field(matrix, score))
      case Failure(_) => None
    }
    data
  }

  def save(field: FieldInterface): Unit ={
    val pw = new PrintWriter(new File("src/main/resources/grid.json" ))
    pw.write(field.toJson.toString())
    pw.close
  }

}
