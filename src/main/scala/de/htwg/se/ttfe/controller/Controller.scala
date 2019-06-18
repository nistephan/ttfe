package de.htwg.se.ttfe.controller

import net.codingwell.scalaguice.InjectorExtensions._
import com.google.inject.{Guice, Inject}
import de.htwg.se.ttfe.TTFEModule
import de.htwg.se.ttfe.model.FileIOInterface
import de.htwg.se.ttfe.model.fieldComponent.FieldInterface
import de.htwg.se.ttfe.model.fieldComponent.fieldBaseImpl.Field

import scala.swing.Publisher

class Controller @Inject() (var field:FieldInterface) extends ControllerInterface with Publisher {

  val injector = Guice.createInjector(new TTFEModule)
  val fileIo = injector.instance[FileIOInterface]
  field = field.start()

  def size: Int ={
    field.size
  }

  def cell(row: Int, col: Int):Int ={
    field.cellsField.cell(row, col)
  }

  def exit: Unit = {
    print("exiting\n")
    sys.exit
  }

  def save: Unit ={
    fileIo.save(field)
    print("Saved!\n")
  }

  def load: Unit ={
    print("Loading\n")
    fileIo.load match {
      case Some(field) =>
        this.field = field
      case None =>
        print("No save!\n")
    }
    publish(new Moved)
  }

  def fieldToString: String = field.toString

  def moveDirection(direction: String):Unit = {
    field = field.moveDirection(direction)
    publish(new Moved)
  }

  def restart(): Unit = {
    field = new Field(size)
    field = field.start()
    publish(new Restarted)
  }

}
