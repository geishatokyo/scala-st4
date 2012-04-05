package com.geishatokyo.scalast4

import java.util.Locale

/**
 *
 * User: takeshita
 * Create: 12/04/05 0:56
 */

trait LocaleFinder {

  def getTemplateNameCandidates( templateName : String, locale : Locale) : Seq[String]

}

class DottedLocaleFinder extends LocaleFinder{
  def getTemplateNameCandidates(templateName: String, locale: Locale) = {

    Seq( templateName + "." + locale.getLanguage + "." + locale.getCountry,
      templateName + "." + locale.getLanguage,
      templateName
    )

  }
}

class UnderBarLocaleFinder extends LocaleFinder{
  def getTemplateNameCandidates(templateName: String, locale: Locale) = {

    Seq( templateName + "_" + locale.getLanguage + "_" + locale.getCountry,
      templateName + "_" + locale.getLanguage,
      templateName
    )

  }
}
