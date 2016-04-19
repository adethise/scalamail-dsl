package mail

import java.util.Properties
import javax.mail.{MessagingException, Session, Transport}

/**
  * Created by bainos on 19/04/16.
  */
class MailTemplate {

  var (hostname, port) = ("localhost", "2525")
  protected var message: ConfigurableMessage = null

  implicit def stringToMessage(emitter: String): ConfigurableMessage = {

    val properties: Properties = System.getProperties
    properties.setProperty("mail.smtp.host", hostname)
    properties.setProperty("mail.smtp.port", port)
    val session: Session = Session.getDefaultInstance(properties)

    message = new ConfigurableMessage(emitter, session)
    message
  }

  def host(name: String) = {
    val hostport = name split ":"
    hostname = hostport(0)
    port = hostport(1)
  }

  def send() = {
    try {
      Transport.send(message lowLevel())
      println("Sent message successfully....")
    }
    catch {
      case mex: MessagingException => mex.printStackTrace()
    }
  }

}
