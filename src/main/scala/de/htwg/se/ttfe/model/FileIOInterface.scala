package de.htwg.se.ttfe.model

import de.htwg.se.ttfe.model.fieldComponent.fieldBaseImpl.Field

class FileIOInterface {

  trait FileIOInterface {

    def load:Field
    def save(grid:Field):Unit

  }

}
