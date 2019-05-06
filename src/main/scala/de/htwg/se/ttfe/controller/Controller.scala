package de.htwg.se.ttfe.controller

import java.io.{File, PrintWriter}
import net.codingwell.scalaguice.InjectorExtensions._
import com.google.inject.{Guice, Inject}
import com.google.inject.name.Names
import de.htwg.se.ttfe.TTFEModule
import de.htwg.se.ttfe.model.FileIOInterface
import de.htwg.se.ttfe.model.fieldComponent.FieldInterface

import scala.swing.Publisher

class Controller @Inject() (var field:FieldInterface) extends ControllerInterface with Publisher {

  val injector = Guice.createInjector(new TTFEModule)
  val fileIo = injector.instance[FileIOInterface]
  field = field.start()
  /*def createNewField(size:Int):Unit = {
    field = new Field(size)
    notifyObservers
  }*/

  def size: Int ={
    field.size
  }

  def cell(row: Int, col: Int):Int ={
    field.cellsField.cell(row, col)
  }

  def exit: Unit = {
    fileIo.save(field)
    print("exiting\n")
    sys.exit
  }

  def fieldToString: String = field.toString

  def moveDirection(direction: String):Unit = {
    field = field.moveDirection(direction)
    publish(new Moved)
  }

  def restart(): Unit = {
    //field.restart()
    publish(new Restarted)
  }

}
