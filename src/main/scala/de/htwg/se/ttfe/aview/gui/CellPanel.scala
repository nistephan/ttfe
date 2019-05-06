package de.htwg.se.ttfe.aview.gui

import de.htwg.se.ttfe.controller.Controller

import scala.swing._

class CellPanel(row: Int, column: Int, controller: Controller) extends FlowPanel {
  def myCell = controller.field.cells.cell(row, column)
  val label =
    new Label {
      text = myCell.toString
      font = new Font("Verdana", 1, 36)
    }

  val cell = new BoxPanel(Orientation.Vertical) {
    contents += label
  }

  contents += cell

  def redraw = {
    contents.clear()
    label.text = myCell.toString
    contents += cell
    repaint
  }

}
