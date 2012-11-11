// -*- mode: Scala;-*-
// Filename:    KvdbPlatformAgentSingleTest.scala
// Authors:     lgm
// Creation:    Tue Apr  5 20:51:35 2011
// Copyright:   Not supplied
// Description:
// ------------------------------------------------------------------------

package com.protegra.agentservicesstore

import org.specs2.mutable._

import org.specs2.runner._
import org.junit.runner._

import com.biosimilarity.lift.model.store._
import com.protegra.agentservicesstore.extensions.StringExtensions._
import com.protegra.agentservicesstore.extensions.ResourceExtensions._
import com.protegra.agentservicesstore.extensions.URIExtensions._

import scala.util.continuations._

import java.net.URI
import java.util.UUID

import com.protegra.agentservicesstore.usage.AgentKVDBScope._
import com.protegra.agentservicesstore.usage.AgentKVDBScope.acT._
import com.protegra.agentservicesstore.usage.AgentKVDBScope.mTT._
import com.protegra.agentservicesstore.usage.AgentUseCase._

import Being.AgentKVDBNodeFactory

class KvdbPlatformAgentSingleTest extends KvdbPlatformAgentBase
{
  sequential

  val timeoutBetween = 0

  val sourceAddress = "127.0.0.1".toURI
  val acquaintanceAddresses = List[ URI ]()
  val writer = createNode(sourceAddress, acquaintanceAddresses)
  val reader = writer

  testMessaging(writer, reader)
  testWildcardWithPut(writer, reader)
  testWildcardWithStore(writer, reader)
  testWildcardWithPutAndCursor(writer, reader)
  testWildcardWithStoreAndCursor(writer, reader)
//  testWildcardWithCursorBefore(writer, reader)

  val sourceId = UUID.randomUUID
  val targetId = sourceId
  val cnxn = new AgentCnxn(sourceId.toString.toURI, "", targetId.toString.toURI)
  val cnxnRandom = new AgentCnxn("Random".toURI, "", UUID.randomUUID.toString.toURI)


  "Store" should {
    "not pin cpu at 100% for 1" in {
      skipped("isolate")
      val key = "disclosedData(\"123\")".toLabel
      val value = "not pin cpu 1@protegra.com"
      writer.store(cnxn)(key, Ground(value))
      fetchMustBe(value)(reader, cnxn, key)
    }

    "not pin cpu at 100% for 5" in {
      skipped("isolate")
      val key1 = "disclosedData(\"1\")".toLabel
      val key2 = "disclosedData(\"2\")".toLabel
      val key3 = "disclosedData(\"3\")".toLabel
      val key4 = "disclosedData(\"4\")".toLabel
      val key5 = "disclosedData(\"5\")".toLabel

      val value = "not pin cpu 1@protegra.com"
      writer.store(cnxn)(key1, Ground(value))
      writer.store(cnxn)(key2, Ground(value))
      writer.store(cnxn)(key3, Ground(value))
      writer.store(cnxn)(key4, Ground(value))
      writer.store(cnxn)(key5, Ground(value))
      fetchMustBe(value)(reader, cnxn, key1)
    }

    "not pin cpu at 100% for 5 complex keys" in {
      skipped("isolate")
      val key1 = "disclosedData(fields(id(\"1111d264-dfe2-43a9-b161-1366439f7bbd\"),localeCode(\"en\"),dataClassType(\"class com.protegra.protunityservices.schema.Role\"),connectionType(\"Basic\"),fields(\"\")))".toLabel
      val key2 = "disclosedData(fields(id(\"2222d264-dfe2-43a9-b161-1366439f7bbd\"),localeCode(\"en\"),dataClassType(\"class com.protegra.protunityservices.schema.Role\"),connectionType(\"Basic\"),fields(\"\")))".toLabel
      val key3 = "disclosedData(fields(id(\"3333d264-dfe2-43a9-b161-1366439f7bbd\"),localeCode(\"en\"),dataClassType(\"class com.protegra.protunityservices.schema.Role\"),connectionType(\"Basic\"),fields(\"\")))".toLabel
      val key4 = "disclosedData(fields(id(\"4444d264-dfe2-43a9-b161-1366439f7bbd\"),localeCode(\"en\"),dataClassType(\"class com.protegra.protunityservices.schema.Role\"),connectionType(\"Basic\"),fields(\"\")))".toLabel
      val key5 = "disclosedData(fields(id(\"5555d264-dfe2-43a9-b161-1366439f7bbd\"),localeCode(\"en\"),dataClassType(\"class com.protegra.protunityservices.schema.Role\"),connectionType(\"Basic\"),fields(\"\")))".toLabel

      val value = "not pin cpu 1@protegra.com"
      writer.store(cnxn)(key1, Ground(value))
      writer.store(cnxn)(key2, Ground(value))
      writer.store(cnxn)(key3, Ground(value))
      writer.store(cnxn)(key4, Ground(value))
      writer.store(cnxn)(key5, Ground(value))
      fetchMustBe(value)(reader, cnxn, key1)
    }
  }

  //  "Get" should {
  //    "not find when key is missing" in {
  //      val key = "contentResponse(nonexistingGet(\"not here\"))".toLabel
  //      getMustBe("")(reader, cnxn, key)
  //    }
  //  }
  //
  //  "Fetch" should {
  //    "not find when key is missing" in {
  //      val key = "contentResponse(nonexistingFetch(\"not here\"))".toLabel
  //      fetchMustBe("")(reader, cnxn, key)
  //    }
  //  }

}
