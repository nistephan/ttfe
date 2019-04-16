package de.htwg.se.ttfe.model

import play.api.libs.json.{JsValue, Json}

import scala.io.Source

class FileIO extends FileIOInterface {

   def load: Field = {
     val source: String = Source.fromFile("src/main/resources/grid.json").getLines.mkString
     val json: JsValue = Json.parse(source)
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
  }

}
