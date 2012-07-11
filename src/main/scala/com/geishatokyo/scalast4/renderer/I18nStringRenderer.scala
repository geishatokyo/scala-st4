package com.geishatokyo.scalast4.renderer

import com.geishatokyo.scalast4.AttributeRenderer
import java.util.{ResourceBundle, Locale}

/**
 *
 * User: takeshita
 * Create: 12/04/05 16:56
 */

class I18nStringRenderer(baseName : String,encoding : String = "shift-jis") extends AttributeRenderer[String] {

  val resources = new I18nResource(encoding)

  def formatString(o: String, format: Option[String], locale: Locale) = {
    format match{
      case Some("i18n") => {
        val props = resources.getProps(baseName,locale)
        props.getOrElse(o,o)
      }
      case None => o
    }
  }
}
