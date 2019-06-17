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

  def readFile(filename: String): Try[String] = {
    Try(Source.fromFile(filename).getLines.mkString)
  }

  def load: FieldInterface = {
    //val injector = Guice.createInjector(new TTFEModule)
    readFile("src/main/resources/grid.json") match {
      case Success(v) =>
        val json: JsValue = Json.parse(v)
        val size = (json \ "size").get.toString.toInt
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
        new Field(matrix)
      case Failure(f) =>
        println("Error: " + f.getMessage)
        new Field(4)
    }
  }

  def save(field: FieldInterface): Unit ={
    val pw = new PrintWriter(new File("src/main/resources/grid.json" ))
    pw.write(field.toJson.toString())
    pw.close
  }

}
