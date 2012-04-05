package com.geishatokyo

import java.util.HashMap

/**
 *
 * User: takeshita
 * Create: 12/04/05 21:55
 */

package object scalast4{


  /**
   * utility method for java list
   * @param objs
   * @return
   */
  def jlist( objs : Any*) : java.util.List[AnyRef] = {
    val list = new java.util.ArrayList[AnyRef](objs.size)
    objs.foreach(o => list.add(o.asInstanceOf[AnyRef]))
    list
  }

  /**
   * utility methof for java map
   * @param kvs
   * @return
   */
  def jmap( kvs : (String,Any) * ) : java.util.Map[String,AnyRef] = {
    val map = new java.util.HashMap[String,AnyRef]()
    kvs.foreach(kv => {
      map.put(kv._1,kv._2.asInstanceOf[AnyRef])
    })
    map
  }

}
