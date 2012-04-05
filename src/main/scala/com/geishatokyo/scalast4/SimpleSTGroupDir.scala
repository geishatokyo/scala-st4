package com.geishatokyo.scalast4

import org.stringtemplate.v4.{STGroup => JSTGoup}
import java.io.{FileInputStream, File}
import org.antlr.runtime.ANTLRInputStream

/**
 *
 * User: takeshita
 * Create: 12/04/05 2:21
 */

class SimpleSTGroupDir(dirPath : String) extends JSTGoup {

  val dir = new File(dirPath)
  encoding = "utf-8"

  override def load(name: String) = {

    //TODO remove println
    val f = new File(dir,name + ".st")
    println("File:" + f.getAbsoluteFile )
    if(f.exists()){
      println("File:" + f.getAbsoluteFile )
      val fs = new ANTLRInputStream(new FileInputStream(f),encoding)
      loadTemplateFile("",name,fs)
    }else {
      val stream = Thread.currentThread().getContextClassLoader.getResourceAsStream(name + ".st")
      if (stream != null){
        val fs = new ANTLRInputStream(stream,encoding)
        loadTemplateFile("",name,fs)
      }else{
        null
      }
    }
  }
}
