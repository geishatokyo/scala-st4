package com.geishatokyo.scalast4

import org.specs2.SpecificationWithJUnit
import java.util.Locale

/**
 *
 * User: takeshita
 * Create: 12/04/05 1:13
 */

class LocaleFinderTest extends SpecificationWithJUnit { def is =
  "getTemplateCandidates" ^
    "should get locale appended filenames" ! getTemplateNames ^ end


  def getTemplateNames = {
    val finder = new UnderBarLocaleFinder
    finder.getTemplateNameCandidates("fuga",Locale.JAPAN) must_== List("fuga_ja_JP","fuga_ja","fuga")
  }

}
