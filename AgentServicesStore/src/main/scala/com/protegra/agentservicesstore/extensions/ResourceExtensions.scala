/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.protegra.agentservicesstore.extensions

import com.protegra.agentservicesstore.AgentTS.mTT._
import scala.util.continuations._

//don't import DirectedLinks.SpecialKURIDefaults, or AgentLinks.AgentURIDefaults when using this
object ResourceExtensions
{
  implicit def resourceExt(source: Resource) = new ResourceExt(source)

  class ResourceExt(source: Resource) {
    def dispatch :String = {
      source match {
        case RBoundHM(Some( Ground( gv ) ), _ ) => {
            gv.toString
          }
        case _ => {println("dispatch: ground value not found");new String}
      }
    }
  }

  implicit def resourceOptionExt(source: Option[Resource]) = new ResourceOptionExt(source)
  
  class ResourceOptionExt(source: Option[Resource]) {
    def dispatch :String = {
      val message = source.getOrElse(new Ground(new String))
      message match {
        case RBoundHM(Some( Ground( gv ) ), _ ) => {
            gv.toString
          }
        case _ => {println("dispatch: ground value not found");new String}
      }
    }

    def dispatchCursor : Generator[Resource, Unit, Unit] = {
      val message = source.getOrElse(new Ground(new String))
      message match {
        case RBoundHM(Some( Cursor( iterator: Generator[Resource, Unit, Unit] ) ), _ ) => {
            iterator
          }
        case _ => {
          println("dispatch: cursor not found");
          val iterator: Generator[Resource, Unit, Unit] = itergen[Resource]( Nil )
          iterator
        }
      }
    }
  }

  implicit def resourceGeneratorExt(source: Generator[Resource, Unit, Unit]) = new ResourceGeneratorExt(source)

  class ResourceGeneratorExt(source: Generator[Resource, Unit, Unit]) {
    def toList: List[Resource] = {
      var results: List[ Resource ] = Nil
      reset{
        for ( resource <- source ) {
          results = results ::: List[Resource](resource)
        }
      }
      results
    }
  }
}
