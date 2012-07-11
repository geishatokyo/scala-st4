package com.geishatokyo.scalast4.renderer

import java.io.{BufferedReader, InputStreamReader, InputStream}
import collection.mutable

/**
 *
 * User: takeshita
 * Create: 12/07/11 14:45
 */

object UnicodedPropertyLoader {

  def load( stream : InputStream, charEncoding : String = "utf-8") : Map[String,String] = {

    val reader = new BufferedReader( new InputStreamReader(stream,charEncoding))
    val props = new mutable.LinkedHashMap[String,String]
    var lineIndex = 1
    var line = reader.readLine()
    while(line != null){
      if (line.size == 0){
        // empty!
      }else{
        val c = line.charAt(0)
        if (c == '#' || c == '!'){
          // comment line
        }else{
          props +=( splitKeyAndValue(lineIndex,line))
        }
      }
      line = reader.readLine()
      lineIndex += 1
    }

    props.toMap
  }

  def splitKeyAndValue(lineIndex : Int,line : String) = {
    val i = line.indexOf("=")
    if(i <= 0){
      throw new Exception("Wrong format property@line:%s (%s)".format(lineIndex,line))
    }
    line.substring(0,i) -> line.substring(i+1)

  }

}
