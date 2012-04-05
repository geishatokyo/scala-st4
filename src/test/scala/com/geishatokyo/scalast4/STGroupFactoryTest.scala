package com.geishatokyo.scalast4

import org.specs2.SpecificationWithJUnit
import java.util.Locale

/**
 *
 * User: takeshita
 * Create: 12/04/05 13:28
 */

class STGroupFactoryTest extends SpecificationWithJUnit { def is =

 "fromFiles"               ^
    "should merge some stg files"  ! createFromFiles ^
                                                          end

  def createFromFiles = {
    val g = STGroup.fromFiles("stg/group1.stg","stg/group2.stg")
    g("g1",Locale.JAPAN).add("name" -> "Taro","dummy" -> "dummy","gender" -> "male").render must_== "ok Taro male"

  }

}
