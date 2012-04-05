package com.geishatokyo.scalast4

import org.stringtemplate.v4.{ST => JST}
import io.Source
import java.io.{FileInputStream, File}
import java.util.Locale

/**
 * ST wrapper
 * delimita is changed to $ by default
 * User: takeshita
 * Create: 12/04/04 19:35
 */

case class ST( st : JST) {

  var locale : Option[Locale] = None

  def render = locale match{
    case Some(locale) => st.render(locale)
    case None => st.render
  }

  def render(locale : Locale) = st.render(locale)

  def add( key : String , value : Any) = {
    st.add(key,value)
    this
  }

  def add( keyValues : (String,Any)*) = {
    keyValues.foreach( kv => {
      st.add(kv._1,kv._2)
    })
    this
  }

  def setLocale(locale : Locale) = {
    this.locale = Option(locale)
    this
  }

}

object ST{

  var defaultDelimiter : Option[Char] = Some('$')

  def fromString( str : String) = {
    defaultDelimiter match{
      case Some(c) => ST(new JST(str,'$','$'))
      case None => ST(new JST(str))
    }

  }

  def fromFile(file: String) : ST = {
    fromFile(new File(file))
  }

  def fromFile( file : File) : ST = {
    val io = new FileInputStream(file)
    try{
      val bytes = new Array[Byte](io.available())
      io.read(bytes)
      fromString(new String(bytes,"utf-8"))
    }finally{
      io.close()
    }
  }

  def format( template : String , args : (String, Any) *) = {
    JST.format(template,args.flatMap(arg => List(arg._1.asInstanceOf[AnyRef],arg._2.asInstanceOf[AnyRef])).toArray)
  }
}