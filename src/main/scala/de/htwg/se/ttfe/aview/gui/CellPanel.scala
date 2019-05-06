package de.htwg.se.ttfe.aview.gui

import de.htwg.se.ttfe.controller.{Controller, ControllerInterface}

import scala.swing._

class CellPanel(row: Int, column: Int, controller: ControllerInterface) extends FlowPanel {
  def myCell = controller.cell(row, column)
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
