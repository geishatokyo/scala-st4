package com.geishatokyo.scalast4.renderer

import com.geishatokyo.scalast4.AttributeRenderer
import java.util.{ResourceBundle, Locale}

/**
 *
 * User: takeshita
 * Create: 12/04/05 16:56
 */

class I18nStringRenderer(baseName : String) extends AttributeRenderer[String] {

  def getResourceBundle(locale : Locale) = {
    ResourceBundle.getBundle(baseName,locale)
  }

  def formatString(o: String, format: Option[String], locale: Locale) = {
    println("###" + o + ":" + locale)          // TODO delete
    format match{
      case Some("i18n") => {
        val resourceBundle = getResourceBundle(locale)
        if(resourceBundle.containsKey(o)) resourceBundle.getString(o)
        else o
      }
      case None => o
    }
  }
}
