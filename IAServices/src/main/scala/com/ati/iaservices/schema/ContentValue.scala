package com.ati.iaservices.schema

import com.protegra_ati.agentservices.store.schema.KVDBSerializable
import com.protegra_ati.agentservices.store.util.Reporting

abstract class ContentValue()
  extends Serializable
  with KVDBSerializable
  with Reporting
{

}