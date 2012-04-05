package com.geishatokyo.scalast4

import nextversion.STRawGroupDir
import java.util.Locale
import scala.collection.JavaConversions._
import org.stringtemplate.v4.{ STGroupString, STGroupDir, STGroupFile, STGroup => JSTGoup, ST => JST, AttributeRenderer => JAttributeRenderer}


/**
 *
 * User: takeshita
 * Create: 12/04/04 19:35
 */
case class STGroup( stGroup : JSTGoup) {

  var localeFinder : LocaleFinder = new UnderBarLocaleFinder

  /**
   * get template.
   * same as getInstanceOf
   * @param name
   * @return
   */
  def apply( name : String) : ST = apply(name,Locale.getDefault)

  object ExistTemplate{
    def unapply( name : String) = {
      val t = stGroup.getInstanceOf(name)
      if(t == null) None
      else Some(t)
    }
  }

  def apply( name : String , locale : Locale) : ST = {
    get(name,locale).getOrElse{
      throw new TemplateNotFoundException(name)
    }
  }
  def get(name : String ) : Option[ST] = {
    get(name,Locale.getDefault)
  }
  def get(name : String , locale : Locale) : Option[ST] = {
    val templateNames = localeFinder.getTemplateNameCandidates(name,locale)
    templateNames.collectFirst({
      case ExistTemplate(t) => {
        t.impl.hasFormalArgs = false
        t
      }
    }).map(t => ST(t).setLocale(locale))
  }

  def registerRenderer( attributeType : Class[_], jAttRenderer :  JAttributeRenderer) : STGroup = {
    stGroup.registerRenderer(attributeType,jAttRenderer)
    this
  }
  def registerRenderer( renderer : AttributeRenderer[_]) : STGroup = {
    stGroup.registerRenderer(renderer.m.erasure,renderer)
    this
  }


  /**
   * get template.
   * same as apply
   * @param name
   * @return
   */
  def getInstanceOf(name : String) = apply(name)

  def getInstanceOfWithoutLocale(name : String) = Option(stGroup.getInstanceOf(name)).map(new ST(_)).getOrElse {
    throw new TemplateNotFoundException(name)
  }

  def setLocaleFinder( f : LocaleFinder) ={
    this.localeFinder = f
    this
  }


  def ++( g : STGroup) : STGroup = {
    val parent = new JSTGoup()
    parent.importTemplates(this.stGroup)
    parent.importTemplates(g.stGroup)
    new STGroup(parent).setLocaleFinder(this.localeFinder)
  }


  def generateTemplateFromString( str : String) = {
    val st = new JST(stGroup,str)
    ST(st)
  }

}


object STGroup{

  def plain = new STGroup(changeDelimiter(new JSTGoup()))

  def fromFile(stgFilename:  String) = {
    STGroup(changeDelimiter(new STGroupFile(stgFilename)))
  }

  def fromFiles(stgFilenames : String*) = {
    val parent = changeDelimiter(new JSTGoup())
    stgFilenames.foreach( n => {
      parent.importTemplates(changeDelimiter(new STGroupFile(n)))
    })
    STGroup(parent)

  }

  def fromDir( dirPath : String) = {
    STGroup(changeDelimiter(new STGroupDir(dirPath)))
  }

  def fromEachFileInDir( dirPath : String) = {
    STGroup(changeDelimiter(new STRawGroupDir(dirPath)))
  }

  def fromString( str : String) = {
    STGroup(changeDelimiter(new STGroupString(str)))
  }

  private def changeDelimiter( g : JSTGoup) = {
    ST.defaultDelimiter match{
      case Some(c) =>{
        g.delimiterStartChar = c
        g.delimiterStopChar = c
      }
      case None =>
    }
    g
  }

}
