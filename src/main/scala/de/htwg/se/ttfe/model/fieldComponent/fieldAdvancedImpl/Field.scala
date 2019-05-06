package de.htwg.se.ttfe.model.fieldComponent.fieldAdvancedImpl

import com.google.inject.Inject
import com.google.inject.name.Named
import de.htwg.se.ttfe.model.fieldComponent.fieldBaseImpl.{Field => BaseField}

class Field @Inject() ( @Named("defaultSize") size:Int) extends BaseField(size){


}