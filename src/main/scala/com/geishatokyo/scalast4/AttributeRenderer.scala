package com.geishatokyo.scalast4

import org.stringtemplate.v4.{AttributeRenderer => JAttributeRenderer}
import java.util.Locale

/**
 *
 * User: takeshita
 * Create: 12/04/05 0:44
 */

abstract class AttributeRenderer[T](implicit val m : Manifest[T]) extends JAttributeRenderer {
  def toString(o: AnyRef, format: String, locale: Locale) = {
    formatString(o.asInstanceOf[T],Option(format),locale)
  }

  def formatString( o : T, format : Option[String] , locale : Locale) : String
}
