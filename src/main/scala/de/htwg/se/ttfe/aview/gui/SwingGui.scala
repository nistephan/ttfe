package de.htwg.se.ttfe.aview.gui

import de.htwg.se.ttfe.controller.{Controller, Moved, Restarted}

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._

class SwingGui(controller: Controller) extends Frame {
  listenTo(controller)

  title = "2048"

  var cells = Array.ofDim[CellPanel](controller.field.size, controller.field.size)

  def gridPanel = new GridPanel(controller.field.size, controller.field.size) {
    border = LineBorder(java.awt.Color.BLACK, 2)
    background = java.awt.Color.BLACK

    for(x <- 0 until controller.field.size){
      for(y <- 0 until controller.field.size){
        val cellPanel = new CellPanel(x, y, controller)
        cells(x)(y) = cellPanel
        contents += cellPanel
        listenTo(cellPanel)
      }
    }


  }

  val statusline = new TextField("test", 20)

  contents = new BorderPanel {
    add(gridPanel, BorderPanel.Position.Center)
    add(statusline, BorderPanel.Position.South)
  }
  visible = true


  reactions += {
    case event: Moved     => redraw
    case event: Restarted => redraw
  }

  def redraw = {
    for {
      row <- 0 until controller.field.size
      column <- 0 until controller.field.size
    } cells(row)(column).redraw
    repaint
  }


}
