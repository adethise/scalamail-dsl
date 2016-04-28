package mail

import java.util.Properties
import javax.activation.{DataHandler, FileDataSource}
import javax.mail.Message.RecipientType.{BCC, CC, TO}
import javax.mail.internet.{InternetAddress, MimeBodyPart, MimeMessage, MimeMultipart}
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
	private val message = new MimeMessage(session)
	message.setFrom(new InternetAddress(from))

	private val multipart = new MimeMultipart()

	def this(from: String) = this(from, {
		val properties: Properties = System.getProperties
		properties.setProperty("mail.smtp.host", "localhost")
		properties.setProperty("mail.smtp.port", "2525")
		Session.getDefaultInstance(properties)
	})

	def to(dests: String*) = setRecipients(TO, dests)

	/* Set the recipients */
	def setRecipients(mode: Message.RecipientType, dests: Seq[String]):
	ConfigurableMessage
	= {
		for (dest <- dests) {
			message.addRecipient(mode, new InternetAddress(dest))
		}
		this
	}

	def cc(dests: String*) = setRecipients(CC, dests)

	def bcc(dests: String*) = setRecipients(BCC, dests)

	/* Set the subject */
	def subject(subject: String) = {
		message.setSubject(subject)
	}

	/* Add a file attachment to the email */
	def attachment(filenames: String*): ConfigurableMessage = {
		val userdir = System.getProperty("user.dir") + "/"
		for (filename <- filenames) {
			val path = userdir + filename
			val source = new FileDataSource(path)
			val messageBodyPart = new MimeBodyPart()
			messageBodyPart.setDataHandler(new DataHandler(source))
			messageBodyPart.setFileName(filename)
			multipart.addBodyPart(messageBodyPart)
		}
		this
	}

	/* Set the content of the message (must be html) */
	def content(content: String) = {
		val messageBodyPart = new MimeBodyPart()
		messageBodyPart.setContent(content, "text/plain")
		multipart.addBodyPart(messageBodyPart)

		message.setContent(multipart)
	}

	def HTMLcontent(content: String) = {
		val messageBodyPart = new MimeBodyPart()
		messageBodyPart.setContent(content, "text/html")
		multipart.addBodyPart(messageBodyPart)

		message.setContent(multipart)
	}

	/* Return the low-level message for advanced configuration */
	def lowLevel() = message

}
