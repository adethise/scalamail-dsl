package mail

import javax.mail.Message.RecipientType.{BCC, CC, TO}
import javax.mail.internet.{InternetAddress, MimeMessage}
import javax.mail.{Message, Session}

/**
  * High-level email message.
  * This class acts as a wrapper for the MimeMessage class,
  * providing a high-level DSL to use with MimeTemplate.
  *
  * A new instance is created with a sender (from) and a session.
  *
  * @param from    Message emitter
  * @param session Mail session
  */
class ConfigurableMessage(from: String, session: Session) {

  // low level message
  private var message = new MimeMessage(session)
  message.setFrom(new InternetAddress(from))

  /* Set the recipients */
  def to(dests: String*): ConfigurableMessage = {
    for (dest <- dests) {
      message.addRecipient(TO, new InternetAddress(dest))
    }
    this
  }

  /* Set the recipients */
  def cc(dests: String*): ConfigurableMessage = {
    for (dest <- dests) {
      message.addRecipient(CC, new InternetAddress(dest))
    }
    this
  }

  /* Set the recipients */
  def bcc(dests: String*): ConfigurableMessage = {
    for (dest <- dests) {
      message.addRecipient(BCC, new InternetAddress(dest))
    }
    this
  }

  /* Set the subject */
  def subject(subject: String) = {
    message.setSubject(subject)
  }

  /* Set the content of the message (must be html) */
  /* TODO replace with a multipart object (see MultiPart, MimeBodyPart) */
  def content(content: String) = {
    message.setContent(content, "text/html")
  }

  /* Return the low-level message for advanced configuration */
  def lowLevel() = message

}
