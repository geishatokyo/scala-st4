package com.geishatokyo.scalast4.renderer

import org.specs2.SpecificationWithJUnit
import com.geishatokyo.scalast4.{STGroup, ST}
import java.util.Locale

/**
 *
 * User: takeshita
 * Create: 12/04/05 17:01
 */

class I18nStringRendererTest extends SpecificationWithJUnit { def is =
  "render"              ^
    "should use i18n if format=i18n"   ! useI18n ^
                                                        end

  def useI18n = {
    val g = STGroup.fromFile("i18n/group1.stg").registerRenderer(new I18nStringRenderer("i18n"))
    val t = g("sample")
    t.add("lang" -> "language","dummy" -> "nop")
    t.render must_== "Current language is Japanese"
    t.render(Locale.ENGLISH) must_== "Current language is English"
  }
}
