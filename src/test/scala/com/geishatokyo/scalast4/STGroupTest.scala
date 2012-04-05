package com.geishatokyo.scalast4

import org.specs2.SpecificationWithJUnit
import java.util.Locale

/**
 *
 * User: takeshita
 * Create: 12/04/05 1:10
 */

class STGroupTest extends SpecificationWithJUnit { def is =
  "apply(String,Locale)" ^
    "should get selected language template"  ! selectLanguageTemplate ^
    "should get not localed file"  ! selectDefaultTemplate ^
                                                                             end

  def selectLanguageTemplate = {
    val g = STGroup.fromEachFileInDir("localetest")
    println(g.stGroup.getRootDirURL)
    val t = g("havelang",Locale.JAPAN)
    t.add("name" -> "Taro").render must_== "ok Taro"

    g("havelangcountry",Locale.JAPAN).render must_== "ok"
  }
  def selectDefaultTemplate = {
    val g = STGroup.fromEachFileInDir("localetest")
    println(g.stGroup.getRootDirURL)
    val t = g("nolang",Locale.JAPAN)
    t.render must_== "ok"
  }

}
