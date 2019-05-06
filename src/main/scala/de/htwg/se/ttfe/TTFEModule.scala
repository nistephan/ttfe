package de.htwg.se.ttfe

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import de.htwg.se.ttfe.controller.{Controller, ControllerInterface}
import de.htwg.se.ttfe.model.{FileIO, FileIOInterface}
import de.htwg.se.ttfe.model.fieldComponent.FieldInterface
import de.htwg.se.ttfe.model.fieldComponent.fieldAdvancedImpl.Field
import net.codingwell.scalaguice.ScalaModule


class TTFEModule extends AbstractModule with ScalaModule {
  val defaultFieldSize:Int = 4
  def configure() = {
    bindConstant().annotatedWith(Names.named("defaultSize")).to(defaultFieldSize)
    bind[FieldInterface].to[Field]
    bind[FieldInterface].annotatedWithName("default").toInstance(new Field(4))
    bind[ControllerInterface].to[Controller]

  }
}
