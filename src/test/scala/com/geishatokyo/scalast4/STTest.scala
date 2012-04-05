package com.geishatokyo.scalast4

import org.specs2.SpecificationWithJUnit

/**
 *
 * User: takeshita
 * Create: 12/04/04 20:11
 */

class STTest extends SpecificationWithJUnit {  def is =

  "fromString" ^
    "should load from string" ! fromString ^
                                               end

  def fromString = {
    val st = ST.fromString("My name is <div>$myName$</div>! I like $hobby$")
    st.add("myName" -> "Riki","hobby" -> "LittleBusters!").render must_== "My name is <div>Riki</div>! I like LittleBusters!"
  }


}
