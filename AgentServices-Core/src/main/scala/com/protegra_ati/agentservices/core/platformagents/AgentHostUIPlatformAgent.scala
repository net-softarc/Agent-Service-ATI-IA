package com.protegra_ati.agentservices.core.platformagents

import com.protegra.agentservicesstore.extensions.StringExtensions._

import behaviors._
import com.protegra_ati.agentservices.core.messages.content._
import com.protegra_ati.agentservices.core.messages.login._
import com.protegra_ati.agentservices.core.messages.verifier._
import com.protegra_ati.agentservices.core.messages.invitation._
import com.protegra_ati.agentservices.core.messages.introduction._
import com.protegra_ati.agentservices.core.messages.admin._

import com.protegra.agentservicesstore.AgentTS._
import com.protegra.agentservicesstore.AgentTS.acT._
import com.protegra_ati.agentservices.core.schema._
import net.lag.configgy.Config
import com.biosimilarity.lift.lib.moniker._
import java.util.UUID
import com.protegra.agentservicesstore.util.Severity
import com.protegra_ati.agentservices.core.messages._

class AgentHostUIPlatformAgent extends BasePlatformAgent
with Private
with Listeners
with Applications
with Registration

with ContentResponseSetPrivate
with ContentNotificationSetPrivate
//with SearchResponseSetPrivate
//with ReferralResponseSetPrivate
//with RegistrationResponseSetConsumerPrivate

//with AgentHostUITestData
with LoginResponseSetPrivate
with VerifierResponseSetPrivate
with VerifierNotificationSetPrivate
with InvitationResponseSetConsumerPrivate
with IntroductionResponseSetConsumerPrivate
{
  var _cnxnUIStore = new AgentCnxnProxy("UI".toURI, "", "Store".toURI)
    var _agentSessionId: UUID = null

  override def init(configUtil: Config)
  {
    initPrivate(configUtil)
    initApps(configUtil)
  }

  def initForTest(privateLocation: URM, privateAcquaintanceAddresses: List[ URM ], id: UUID)
  {
    initPrivate(privateLocation, privateAcquaintanceAddresses)
    super.initForTest(id)
  }

  override def loadQueues()
  {
    loadPrivateQueue()
  }

  def startListening()
  {
    report("IN THE UI LISTEN", Severity.Trace)
    listenPrivate(_cnxnUIStore)
  }

  def listenPrivate(cnxn: AgentCnxnProxy) =
  {
    listenPrivateContentResponse(cnxn)
    listenPrivateContentNotification(cnxn)
//    listenPrivateSearchResponse(cnxn)
    listenPrivateVerifierResponse(cnxn)
    listenPrivateVerifierNotification(cnxn)
    listenPrivateLoginResponse(cnxn)
    //    listen(_privateQ, cnxn, Channel.Permission, ChannelType.Notification, handleNotificationsChannel(_: AgentCnxnProxy, _: Message))
    listenPrivateInvitationConsumerResponses(cnxn)
    listenPrivateIntroductionConsumerResponses(cnxn)
//    listenPrivateReferralResponses(cnxn)
//    listenPrivateRegistrationConsumerResponses(cnxn)

    //uncomment these lines if you need to create the test data from scratch...don't forget to recomment them :)
    //createMikesTestConnections();
    //createBrokerTestData();
  }

  def send(msg: Message)
  {
    report("AgentUI sending msg on private queue with msg id: " + msg.ids.id.toString + " and parent id: " + msg.ids.parentId.toString)

    msg match {
      case x: RegistrationRequest =>
      {
        register(x)
      }
      case _ => {
        msg.channelLevel = Some(ChannelLevel.Private)
        //    msg.originCnxn = _cnxnUIStore
        msg.originCnxn = msg.targetCnxn
        send(_privateQ, _cnxnUIStore, msg)
      }
    }
  }

}

