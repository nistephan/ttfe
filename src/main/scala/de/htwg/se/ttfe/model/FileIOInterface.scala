package de.htwg.se.ttfe.model

import de.htwg.se.ttfe.model.fieldComponent.FieldInterface
import de.htwg.se.ttfe.model.fieldComponent.fieldBaseImpl.Field

trait FileIOInterface {
  def load:FieldInterface
  def save(field: FieldInterface): Unit
}
