package mail

import javax.mail.internet.{InternetAddress, MimeMessage}
import javax.mail.{Message, Session}

/**
  * Created by bainos on 19/04/16.
  */
class ConfigurableMessage(from: String, session: Session) {

  private var message = new MimeMessage(session)
  message.setFrom(new InternetAddress(from))

  def to(dests: String*): ConfigurableMessage = {
    for (dest <- dests) {
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(dest))
    }
    this
  }

  def cc(dests: String*): ConfigurableMessage = {
    for (dest <- dests) {
      message.addRecipient(Message.RecipientType.CC, new InternetAddress(dest))
    }
    this
  }

  def bcc(dests: String*): ConfigurableMessage = {
    for (dest <- dests) {
      message.addRecipient(Message.RecipientType.BCC, new InternetAddress(dest))
    }
    this
  }

  def subject(subject: String) = {
    message.setSubject(subject)
  }

  def content(content: String) = {
    message.setContent(content, "text/html")
  }

  def lowLevel() = message

}
