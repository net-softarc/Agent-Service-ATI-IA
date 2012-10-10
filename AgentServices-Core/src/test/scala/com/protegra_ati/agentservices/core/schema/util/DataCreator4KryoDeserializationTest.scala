package com.protegra_ati.agentservices.core.schema.util

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.UUID
import com.protegra_ati.agentservices.core._
import com.protegra_ati.agentservices.core.messages.content._
import com.protegra_ati.agentservices.core.schema._
import com.protegra.agentservicesstore.AgentTS.acT._
import com.protegra_ati.agentservices.core.schema._
import messages._
import invitation.CreateInvitationRequest
import org.specs.runner.JUnit4
import org.specs.runner.ConsoleRunner
import org.specs._
import com.protegra_ati.agentservices.core.Timeouts
import scala.collection.JavaConversions._
import java.io._
import com.rits.cloning.Cloner
import com.esotericsoftware.kryo.Kryo
import java.nio.channels.FileChannel
import com.esotericsoftware.kryo.io._
import scala.None
import org.joda.time.DateTime
import com.protegra.agentservicesstore.extensions.StringExtensions._
import com.protegra_ati.agentservices.core.util.serializer.{Serializer, KryoSerializer}
import org.apache.commons.io.FileUtils

class DataCreator4KryoDeserializationTest
  extends JUnit4(DataCreator4KryoDeserializationTestSpecs)

object DataCreator4KryoDeserializationTestSpecsRunner
  extends ConsoleRunner(DataCreator4KryoDeserializationTestSpecs)

object DataCreator4KryoDeserializationTestSpecs extends Specification with
Timeouts
{
// TODO: until kryo is back in place
//  DataCreator4KryoDeserializationTestInitializer.setup(this)
//
//  //LIST OF IN KRYO NOT SUPPORTED SCALA FEATURES
//  // keyword: "override val" in a constructor except for simple type like string !!!!
//  // keyword: lazy val
//  //
//
//
//
//
//  "serializing mock data" should {
//
//    "serialize mock object with a nested collection" in {
//
//      DataCreator4KryoDeserializationTestInitializer.serialize(MockWithJavaMap.simpleMockHashMapStringString, new File("data//serializedMockWithCollection_StringStringMap.kryobin"))
//
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(MockWithJavaMap.simpleMockHashMapStringString)
//
//      DataCreator4KryoDeserializationTestInitializer.serialize(MockWithJavaMap.simpleMockHashMapStringData, new File("data//serializedMockWithCollection_StringDataMap.kryobin"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(MockWithJavaMap.simpleMockHashMapStringData)
//
//      DataCreator4KryoDeserializationTestInitializer.serialize(MockWithJavaMap.simpleMockHashMapStringMockProfile, new File("data//serializedMockWithCollection_StringMockProfileMap.kryobin"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(MockWithJavaMap.simpleMockHashMapStringMockProfile)
//
//      DataCreator4KryoDeserializationTestInitializer.serialize(MockWithJavaMap.simpleMockHashMapStringProfile, new File("data//serializedMockWithCollection_StringProfileMap.kryobin"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(MockWithJavaMap.simpleMockHashMapStringProfile)
//    }
//
//
//    "serialize simple mock profile" in {
//      // skip("ignore")
//      val mockSimpleProfile = new Cloner().deepClone(KryoSerializerTestDataInitializer.profile1).asInstanceOf[ SimpleMockProfile1 ]
//      mockSimpleProfile.id = "UID"
//      mockSimpleProfile.localeCode = "br"
//      DataCreator4KryoDeserializationTestInitializer.serialize(mockSimpleProfile, new File("data//serializedMocProfile1.kryobin"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(mockSimpleProfile)
//    }
//
//
//
//    "serialize and deserialize composite mock profile" in {
//      // skip("ignore")
//      val compositeMockProfile = new Cloner().deepClone(KryoSerializerTestDataInitializer.compositeProfile).asInstanceOf[ CompositeMockProfile1 ]
//      compositeMockProfile.id = "UID"
//      compositeMockProfile.localeCode = "br"
//      compositeMockProfile.image.id = "nestedUID"
//      compositeMockProfile.image.localeCode = "us"
//      DataCreator4KryoDeserializationTestInitializer.serialize(compositeMockProfile, new File("data//serializedCompositeMocProfile1.kryobin"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(compositeMockProfile)
//    }
//
//
//  }
//
//  "serializing real data" should {
//
//    "serialize composite with EventKey" in {
//      // skip("")
//      val agentSessionId: UUID = UUID.fromString("DE3C8348-94C2-4BF1-966A-EE57C14BA073")
//      val agentSessionId1: UUID = UUID.fromString("AE3C8348-94C2-4BF1-966A-EE57C14BA073")
//      val eventTag = "content"
//      val eventKey = new EventKey(agentSessionId, eventTag)
//      val eventKey1 = new EventKey(agentSessionId1, "eventTag")
//      val moc = new RealCompositeMockObject1(eventKey, eventKey1, "hi", "bai", new DateTime(5000000))
//      DataCreator4KryoDeserializationTestInitializer.serialize(moc, new File("data//serializedCompositeWithEventKey.kryobin"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(moc)
//    }
//
//    "serialize different mock objects" in {
//      // skip("")
//      val anyRefMap = new java.util.HashMap[ String, AnyRef ]();
//      val fromDetails = new java.util.HashMap[ String, Data ]();
//      val toDetails = new java.util.HashMap[ String, Data ]();
//      val tempProfile = new Profile("firstName", "lastName", "description", "emailAddress", "country", "region", "city", "postalCode", "website");
//
//      fromDetails.put(tempProfile.formattedClassName, tempProfile);
//      toDetails.put(tempProfile.formattedClassName, tempProfile);
//
//      val postToBroker = new Post("subject: to Broker", "body", new java.util.HashMap(), fromDetails, "AE3C8348-94C2-4BF1-966A-EE57C14BA073");
//      val postToSerialized = new Post("subject: to Broker", "body", null, toDetails, "AE3C8348-94C2-4BF1-966A-EE57C14BA073");
//
//      DataCreator4KryoDeserializationTestInitializer.serialize(postToBroker, new File("data//serializedMockPost.kryobin"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(postToBroker)
//
//      DataCreator4KryoDeserializationTestInitializer.serialize(postToSerialized, new File("data//serializedPost.kryobin"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(postToSerialized)
//
//      val id: Identification = new Identification("ID", "parent", "convers")
//      DataCreator4KryoDeserializationTestInitializer.serialize(id, new File("data//serializedIdentification.kryobin"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(id)
//
//      val agentSessionId: UUID = UUID.fromString("DE3C8348-94C2-4BF1-966A-EE57C14BA073")
//      val agentSessionId1: UUID = UUID.fromString("AE3C8348-94C2-4BF1-966A-EE57C14BA070")
//      val eventTag = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
//      val brokerId = "Broker" + UUID.fromString("bbbbbbbb-d417-4d71-ad94-8c766907381b")
//      val newConn = ConnectionFactory.createConnection("New Connection", ConnectionCategory.Self.toString, ConnectionCategory.Self.toString, "connectionType", brokerId, brokerId)
//      newConn.id = "conn id"
//      val eventKey = new MockEventKey(agentSessionId, eventTag)
//      val post = new Post("theSubject", "theBody", new java.util.HashMap(), new java.util.HashMap(), "bbbbbbbb-d417-4d71-ad94-8c766907381b")
//      val eventKeyHope = new MockEventKey(agentSessionId1, "UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU")
//      val mockRequest = new MockCreateInvitationRequest(eventKey, eventKeyHope, "invitationConnectionId1", "selfAlias", Some("hallo world"), None, post :: Nil)
//      DataCreator4KryoDeserializationTestInitializer.serialize(mockRequest, new File("data//serializedMockRequest.kryobin"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(mockRequest)
//
//      val mockOption = new MockOptionObject()
//      DataCreator4KryoDeserializationTestInitializer.serialize(mockOption, new File("data//serializedMockOption.kryobin"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(mockOption)
//
//
//      val agentCnxn = new MockAgentCnxnProxy("targetId".toURI, "I'm also here", "sourceId".toURI)
//      DataCreator4KryoDeserializationTestInitializer.serialize(agentCnxn, new File("data//agentCnxn1.kryobin"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(agentCnxn)
//
//      val agentCnxn1 = new MockAgentCnxnProxy("targetId".toURI, "", "sourceId".toURI)
//      DataCreator4KryoDeserializationTestInitializer.serialize(agentCnxn1, new File("data//agentCnxn2.kryobin"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(agentCnxn1)
//
//      val agentCnxn2 = new MockAgentCnxnProxy("targetId".toURI, "Hi", null)
//      DataCreator4KryoDeserializationTestInitializer.serialize(agentCnxn2, new File("data//agentCnxn3.kryobin"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(agentCnxn2)
//
//    }
//
//    "serialize with same reference inside of " in {
//      val reference = new MockAgentCnxnProxy("targetId".toURI, "", "sourceId".toURI)
//      val duplicateHolder = new DuplicateMockObject(reference, reference, "test")
//      DataCreator4KryoDeserializationTestInitializer.serialize(duplicateHolder, new File("data//test.kryobin"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(duplicateHolder)
//
//    }
//
//    "serialize SystemData with Connection" in {
//      val selfCnxn = new AgentCnxnProxy("targetId".toURI, "", "sourceId".toURI)
//      val selfCnxns = new Connection(ConnectionCategory.Self.toString, "Full", "System", selfCnxn, selfCnxn, "false", List[ String ](ConnectionPolicy.DeleteDisabled.toString, ConnectionPolicy.SearchDisabled.toString)) //List[ String ]()) //List[ String ]("DeleteDisabled", "SearchDisabled"))
//      val systemConnection = new SystemData[ Connection ](selfCnxns)
//      DataCreator4KryoDeserializationTestInitializer.serialize(systemConnection, new File("data//serializedSystemConnection.kryobin"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(systemConnection)
//    }
//    "serialize connection using serializer class" in {
//      val selfCnxn = new AgentCnxnProxy("targetId".toURI, "", "sourceId".toURI)
//      val selfCnxns = new Connection(ConnectionCategory.Self.toString, "Full", "System", selfCnxn, selfCnxn, "false", List[ String ](ConnectionPolicy.DeleteDisabled.toString, ConnectionPolicy.SearchDisabled.toString)) //List[ String ]()) //List[ String ]("DeleteDisabled", "SearchDisabled"))
//      DataCreator4KryoDeserializationTestInitializer.serialize(selfCnxns, new File("data//serializedConnection.kryobin"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(selfCnxns)
//    }
//
//    "serialize persisted CreateInvitationRequest" in {
//
//      val agentSessionId: UUID = UUID.fromString("DE3C8348-94C2-4BF1-966A-EE57C14BA073")
//      val eventTag = "content"
//      val eventKey = new EventKey(agentSessionId, eventTag)
//      val fromDetails = new java.util.HashMap[ String, Data ]();
//      val brokerId = "Broker" + UUID.fromString("bbbbbbbb-d417-4d71-ad94-8c766907381b")
//      val newConn = ConnectionFactory.createConnection("New Connection", ConnectionCategory.App.toString, ConnectionCategory.Self.toString, "connectionType", brokerId, brokerId)
//      newConn.id = "conn id"
//      val msg = new CreateInvitationRequest(eventKey, "targetConnectionId", "selfAlias", "targetAlias", "requestedCategory", "requestedConnectionType", "requestedConnectionName", null, null) //, postToTarget, postToBroker);
//      DataCreator4KryoDeserializationTestInitializer.serialize(msg, new File("data//serializedRequest.kryobin"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(msg)
//
//      val persistedMessage = new PersistedMessage[ CreateInvitationRequest ](msg)
//      DataCreator4KryoDeserializationTestInitializer.serialize(persistedMessage, new File("data//serializedPersistedMessageCreateInvitationRequest.kryobin"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(persistedMessage)
//    }
//  }
//
//  "serializing real data into base64String" should {
//
//    "serialize composite with EventKey" in {
//      // skip("")
//      val agentSessionId: UUID = UUID.fromString("DE3C8348-94C2-4BF1-966A-EE57C14BA073")
//      val agentSessionId1: UUID = UUID.fromString("AE3C8348-94C2-4BF1-966A-EE57C14BA073")
//      val eventTag = "content"
//      val eventKey = new EventKey(agentSessionId, eventTag)
//      val eventKey1 = new EventKey(agentSessionId1, "eventTag")
//      val moc = new RealCompositeMockObject1(eventKey, eventKey1, "hi", "bai", new DateTime(5000000))
//
//      DataCreator4KryoDeserializationTestInitializer.serializeAsString(moc, new File("data//serializedCompositeWithEventKey.kryostring"))
//    }
//
//    "serialize different mock objects" in {
//      // skip("")
//      val anyRefMap = new java.util.HashMap[ String, AnyRef ]();
//      val fromDetails = new java.util.HashMap[ String, Data ]();
//      val toDetails = new java.util.HashMap[ String, Data ]();
//      val tempProfile = new Profile("firstName", "lastName", "description", "emailAddress", "country", "region", "city", "postalCode", "website");
//
//      fromDetails.put(tempProfile.formattedClassName, tempProfile);
//      toDetails.put(tempProfile.formattedClassName, tempProfile);
//
//      val postToBroker = new Post("subject: to Broker", "body", new java.util.HashMap(), fromDetails, "AE3C8348-94C2-4BF1-966A-EE57C14BA073");
//      val postToSerialized = new Post("subject: to Broker", "body", null, toDetails, "AE3C8348-94C2-4BF1-966A-EE57C14BA073");
//
//      DataCreator4KryoDeserializationTestInitializer.serializeAsString(postToBroker, new File("data//serializedMockPost.kryostring"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(postToBroker)
//
//      DataCreator4KryoDeserializationTestInitializer.serializeAsString(postToSerialized, new File("data//serializedPost.kryostring"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(postToSerialized)
//
//      val id: Identification = new Identification("ID", "parent", "convers")
//      DataCreator4KryoDeserializationTestInitializer.serializeAsString(id, new File("data//serializedIdentification.kryostring"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(id)
//
//      val agentSessionId: UUID = UUID.fromString("DE3C8348-94C2-4BF1-966A-EE57C14BA073")
//      val agentSessionId1: UUID = UUID.fromString("AE3C8348-94C2-4BF1-966A-EE57C14BA070")
//      val eventTag = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
//      val brokerId = "Broker" + UUID.fromString("bbbbbbbb-d417-4d71-ad94-8c766907381b")
//      val newConn = ConnectionFactory.createConnection("New Connection", ConnectionCategory.Self.toString, ConnectionCategory.Self.toString, "connectionType", brokerId, brokerId)
//      newConn.id = "conn id"
//      val eventKey = new MockEventKey(agentSessionId, eventTag)
//      val post = new Post("theSubject", "theBody", new java.util.HashMap(), new java.util.HashMap(), "bbbbbbbb-d417-4d71-ad94-8c766907381b")
//      val eventKeyHope = new MockEventKey(agentSessionId1, "UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU")
//      val mockRequest = new MockCreateInvitationRequest(eventKey, eventKeyHope, "invitationConnectionId1", "selfAlias", Some("hallo world"), None, post :: Nil)
//      DataCreator4KryoDeserializationTestInitializer.serializeAsString(mockRequest, new File("data//serializedMockRequest.kryostring"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(mockRequest)
//
//      val mockOption = new MockOptionObject()
//      DataCreator4KryoDeserializationTestInitializer.serializeAsString(mockOption, new File("data//serializedMockOption.kryostring"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(mockOption)
//
//
//      val agentCnxn = new MockAgentCnxnProxy("targetId".toURI, "I'm also here", "sourceId".toURI)
//      DataCreator4KryoDeserializationTestInitializer.serializeAsString(agentCnxn, new File("data//agentCnxn1.kryostring"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(agentCnxn)
//
//      val agentCnxn1 = new MockAgentCnxnProxy("targetId".toURI, "", "sourceId".toURI)
//      DataCreator4KryoDeserializationTestInitializer.serializeAsString(agentCnxn1, new File("data//agentCnxn2.kryostring"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(agentCnxn1)
//
//      val agentCnxn2 = new MockAgentCnxnProxy("targetId".toURI, "Hi", null)
//      DataCreator4KryoDeserializationTestInitializer.serializeAsString(agentCnxn2, new File("data//agentCnxn3.kryostring"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(agentCnxn2)
//
//    }
//
//    "serialize with same reference inside of " in {
//      val reference = new MockAgentCnxnProxy("targetId".toURI, "", "sourceId".toURI)
//      val duplicateHolder = new DuplicateMockObject(reference, reference, "test")
//      DataCreator4KryoDeserializationTestInitializer.serializeAsString(duplicateHolder, new File("data//test.kryostring"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(duplicateHolder)
//
//    }
//
//    "serialize SystemData with Connection" in {
//      val selfCnxn = new AgentCnxnProxy("targetId".toURI, "", "sourceId".toURI)
//      val selfCnxns = new Connection(ConnectionCategory.Self.toString, "Full", "System", selfCnxn, selfCnxn, "false", List[ String ](ConnectionPolicy.DeleteDisabled.toString, ConnectionPolicy.SearchDisabled.toString)) //List[ String ]()) //List[ String ]("DeleteDisabled", "SearchDisabled"))
//      val systemConnection = new SystemData[ Connection ](selfCnxns)
//      DataCreator4KryoDeserializationTestInitializer.serializeAsString(systemConnection, new File("data//serializedSystemConnection.kryostring"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(systemConnection)
//    }
//    "serialize connection using serializer class" in {
//      val selfCnxn = new AgentCnxnProxy("targetId".toURI, "", "sourceId".toURI)
//      val selfCnxns = new Connection(ConnectionCategory.Self.toString, "Full", "System", selfCnxn, selfCnxn, "false", List[ String ](ConnectionPolicy.DeleteDisabled.toString, ConnectionPolicy.SearchDisabled.toString)) //List[ String ]()) //List[ String ]("DeleteDisabled", "SearchDisabled"))
//      DataCreator4KryoDeserializationTestInitializer.serializeAsString(selfCnxns, new File("data//serializedConnection.kryostring"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(selfCnxns)
//    }
//
//    "serialize persisted CreateInvitationRequest" in {
//
//      val agentSessionId: UUID = UUID.fromString("DE3C8348-94C2-4BF1-966A-EE57C14BA073")
//      val eventTag = "content"
//      val eventKey = new EventKey(agentSessionId, eventTag)
//      val fromDetails = new java.util.HashMap[ String, Data ]();
//      val brokerId = "Broker" + UUID.fromString("bbbbbbbb-d417-4d71-ad94-8c766907381b")
//      val newConn = ConnectionFactory.createConnection("New Connection", ConnectionCategory.App.toString, ConnectionCategory.Self.toString, "connectionType", brokerId, brokerId)
//      newConn.id = "conn id"
//      val msg = new CreateInvitationRequest(eventKey, "targetConnectionId", "selfAlias", "targetAlias", "requestedCategory", "requestedConnectionType", "requestedConnectionName", null, null) //, postToTarget, postToBroker);
//      DataCreator4KryoDeserializationTestInitializer.serializeAsString(msg, new File("data//serializedRequest.kryostring"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(msg)
//
//      val persistedMessage = new PersistedMessage[ CreateInvitationRequest ](msg)
//      DataCreator4KryoDeserializationTestInitializer.serializeAsString(persistedMessage, new File("data//serializedPersistedMessageCreateInvitationRequest.kryostring"))
//      DataCreator4KryoDeserializationTestInitializer.checkIfKryoSerializable(persistedMessage)
//    }
//  }
//
//}
//
//object DataCreator4KryoDeserializationTestInitializer extends Specification
//{
//
//  val profile1: SimpleMockProfile1 = SimpleMockProfile1("firstName", "lastName", "description", "emailAddress", "country", "region", "city", "postalCode")
//  val compositeProfile: CompositeMockProfile1 = CompositeMockProfile1("firstName", "lastName", "description", "emailAddress", "country", "region", "city", "postalCode", MockImage.simpleDemoMockImage)
//
//
//  val setup = new SpecContext
//  {
//    if ( !new File("data").exists() ) {
//      new File("data").mkdir()
//    }
//    System.err.println(" in KryoSerializerTestDataInitializer constructor")
//  }
//
//
//  def serialize(data: Object, toFile: File): Unit =
//  {
//
//    try {
//      if ( toFile.exists() ) {toFile.delete()}
//    } catch {
//      case ex: Exception => {
//        /* TODO into log file*/
//        System.err.println("something different:" + ex.toString)
//        ex.printStackTrace();
//        fail("previously serialized class can't be deleted")
//      }
//    }
//    serialize(data, new FileOutputStream(toFile))
//  }
//
//
//  def serialize(data: Object, toStream: FileOutputStream): Unit =
//  {
//    val _serializer = KryoSerializer.getInstance()
//
//    val out: BufferedOutputStream = new BufferedOutputStream(toStream)
//    try {
//      _serializer.serialize(data, out)
//
//    } finally {
//      try {
//        if ( out != null ) {
//          out.close();
//        }
//      } catch {
//        case ex: IOException => {/* TODO into log file*/}
//        case _ => {/* TODO into log file*/}
//      }
//
//    }
//  }
//
//
//  def deserialize[ T ](fromInputStream: InputStream): T =
//  {
//    KryoSerializer.getInstance().deserialize[ T ](fromInputStream)
//
//  }
//
//  def deserialize[ T ](file: File): T =
//  {
//    return deserialize[ T ](new BufferedInputStream(new FileInputStream(file)))
//
//  }
//
//  def deserializeLikeProductive[ T ](fromInputStream: InputStream): T =
//  {
//    KryoSerializer.getInstance().deserialize[ T ](fromInputStream)
//
//  }
//
//  def checkIfKryoSerializable(data: Object): Unit =
//  {
//    if ( !Serializer.evaluateSerializerClass(data).equals(KryoSerializer.getInstance().getClass.getName) )
//      fail("not using KryoSerializer")
//  }
//
//
//  def serializeAsString(data: Object, f: File) =
//  {
//    try {
//      FileUtils.writeStringToFile(f, Serializer.serialize(data), "UTF-8")
//    } catch {
//      case ex: IOException => {/* TODO into log file*/}
//      case _ => {/* TODO into log file*/}
//    }
//  }
//
//
//  def deserializeFromString[ T ](from: File): T =
//  {
//    try {
//      val base64 = FileUtils.readFileToString(from, "UTF-8")
//      return Serializer.deserialize[ T ](base64)
//    } catch {
//      case ex: IOException => {
//        /* TODO into log file*/
//        System.err.println("ERROR BY DESERIALIZATION")
//        ex.printStackTrace()
//        return null.asInstanceOf[ T ]
//      }
//      case ex: Exception => {
//        /* TODO into log file*/
//        System.err.println("ERROR BY DESERIALIZATION")
//        ex.printStackTrace()
//        return null.asInstanceOf[ T ]
//      }
//      case _ => {
//        System.err.println("ERROR BY DESERIALIZATION")
//        /* TODO into log file*/
//        return null.asInstanceOf[ T ]
//      }
//    }
//  }
//

}