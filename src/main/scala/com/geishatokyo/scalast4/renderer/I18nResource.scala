package com.geishatokyo.scalast4.renderer

import java.util.Locale

import scala.collection.mutable
import java.io.FileNotFoundException
import java.util.logging.Logger

class I18nResource(encoding : String){

  val logging = Logger.getLogger(getClass.getName)

  type Properties = Map[String,String]

  trait CacheStatus
  case class NotFound() extends CacheStatus
  case class Loaded(prop : Properties) extends CacheStatus
  case class Ref(prop : Properties) extends CacheStatus

  val resources = new scala.collection.mutable.HashMap[String,CacheStatus]()
    with mutable.SynchronizedMap[String,CacheStatus]
  
  def getProps(baseName : String, locale : Locale) : Map[String,String] = {
    val names = listUpNames(baseName,locale)

    var prop : Option[(Properties,Boolean)] = None
    names.find(n => {
      prop = tryLoad(n)
      prop.isDefined
    })
    prop match{
      case Some((p,loadedNow_?)) => {
        if(loadedNow_?){
          registerRefs(names,p)
        }
        p
      }
      case None => {
        throw new FileNotFoundException(
          "Property file for (name:%s locale:Locale) is not found".format(baseName,locale))
      }
    }
  }

  private def listUpNames(baseName : String,locale : Locale) = {
    if(locale.getVariant.length > 0){
      Seq(
        baseName + "_" + locale.getLanguage + "_" + locale.getCountry + "_" + locale.getVariant,
        baseName + "_" + locale.getLanguage + "_" + locale.getCountry ,
        baseName + "_" + locale.getLanguage,
        baseName
      )
    }else if (locale.getCountry.length > 0){
      Seq(
        baseName + "_" + locale.getLanguage + "_" + locale.getCountry ,
        baseName + "_" + locale.getLanguage,
        baseName
      )
    }else{
      Seq(
        baseName + "_" + locale.getLanguage,
        baseName
      )

    }
  }

  val Extension = ".properties"

  private def tryLoad(name : String) : Option[(Properties,Boolean)] = {
    resources.get(name) match{
      case Some(Loaded(prop)) => Some(prop -> false)
      case Some(Ref(prop)) => {
        loadProp(name) match{
          case Some(p) => {
            resources +=(name -> Loaded(p))
            Some(p -> true)
          }
          case None => {
            resources +=(name -> Loaded(prop))
            Some(prop -> false)
          }
        }
      }
      case Some(NotFound()) => None
      case _ => {
        loadProp(name) match{
          case Some(p) => {
            resources +=(name -> Loaded(p))
            Some(p -> true)
          }
          case None => {
            resources +=( name -> NotFound())
            None
          }
        }
      }
    }
  }

  private def loadProp(name : String) = {
    val classLoader = Thread.currentThread().getContextClassLoader
    val stream = classLoader.getResourceAsStream(name + Extension)
    if(stream != null){
      logging.info("Load prop:" + name + Extension)
      try{
        val prop = UnicodedPropertyLoader.load(stream,encoding)
        Some(prop)
      }finally{
        stream.close()
      }
    }else{
      None
    }
  }

  private def registerRefs(names : Seq[String], prop : Properties) = {
    names.foreach(name => {
      resources.get(name) match{
        case Some(Loaded(_)) =>
        case Some(Ref(_)) =>
        case Some(NotFound()) => resources += (name -> Ref(prop))
        case None => resources += (name -> Ref(prop))
      }
    })
  }


}
