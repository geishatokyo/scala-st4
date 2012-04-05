package com.geishatokyo.scalast4.nextversion

import org.antlr.runtime.CharStream
import org.stringtemplate.v4.compiler._
import org.stringtemplate.v4.compiler.Compiler
import org.stringtemplate.v4.misc.Misc

import java.net.URL
import org.stringtemplate.v4.STGroupDir
;
/**
 *
 * This class is copy from 4.0.4-SNAPSHOT
 * TODO If this class is supported formally, delete this class.
 * User: takeshita
 * Create: 12/04/05 13:06
 */

class STRawGroupDir(dirName : String,encoding : String,delimiterStartChar : Char,delimiterStopChar : Char) extends STGroupDir(dirName,encoding,delimiterStartChar,delimiterStopChar){

  def this(dirName : String) = this(dirName,"utf-8",'$','$')

  override def loadTemplateFile(prefix : String, unqualifiedFileName : String, templateStream : CharStream) =
  {
    val template = templateStream.substring(0, templateStream.size() - 1);
    val templateName = Misc.getFileNameNoSuffix(unqualifiedFileName);
    val impl = new Compiler(this).compile(templateName, template);
    impl.prefix = prefix;
    impl
  }

}
