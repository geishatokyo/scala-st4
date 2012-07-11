package com.geishatokyo.scalast4.renderer

import org.specs2.mutable.SpecificationWithJUnit
import com.geishatokyo.scalast4.{STGroup, ST}
import java.util.Locale

/**
 *
 * User: takeshita
 * Create: 12/04/05 17:01
 */

class I18nStringRendererTest extends SpecificationWithJUnit {
  "render" should{
    "should use i18n if format=i18n" in {

      val g = STGroup.fromFile("i18n/group1.stg").registerRenderer(new I18nStringRenderer("i18n"))
      val t = g("sample")
      t.add("lang" -> "language","dummy" -> "nop")
      t.render(Locale.JAPANESE) must_== "Current language is Japanese"
      t.render(Locale.ENGLISH) must_== "Current language is English"
      //no language file
      t.render(Locale.CHINESE) must_== "Current language is English"
    }

    "unicode chars" in{
      val g = STGroup.fromFile("i18n/group1.stg").registerRenderer(new I18nStringRenderer("i18n"))
      val t = g.generateTemplateFromString("""$greeting;format="i18n"$""")
      t.add("greeting" -> "hello")

      t.render(Locale.JAPANESE)  must_== "こんにちは"
      t.render(Locale.ENGLISH) must_== "Hello !"
      // no language file
      t.render(Locale.GERMAN) must_== "Helloooooo"
    }
  }
}
