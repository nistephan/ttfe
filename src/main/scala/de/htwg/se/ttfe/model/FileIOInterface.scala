package de.htwg.se.ttfe.model

class FileIOInterface {

  trait FileIOInterface {

    def load:Field
    def save(grid:Field):Unit

  }

}
