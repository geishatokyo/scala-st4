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
    "should replace with map and list" ! replaceMapAndList ^
                                               end

  def fromString = {
    val st = ST.fromString("My name is <div>$myName$</div>! I like $hobby$")
    st.add("myName" -> "Riki","hobby" -> "LittleBusters!","dummy" -> "dummyParam").render must_== "My name is <div>Riki</div>! I like LittleBusters!"

    println("--- " + ST.fromString("あいうえおかきくけこさしすせそ$hobby$").add("name" -> "がぎぐげご").render)

    this.ok
  }

  def replaceMapAndList = {
    val st = ST.fromString("""He is $he.name$.And he likes $he.hobby;separator=" and "$""")
    st.add("he" -> jmap("name" -> "Tom","hobby" -> jlist("running","baseball"))).render must_== "He is Tom.And he likes running and baseball"
  }


}
