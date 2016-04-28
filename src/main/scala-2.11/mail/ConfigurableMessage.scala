package mail

import javax.activation.{DataHandler, FileDataSource}
import javax.mail.Message.RecipientType.{BCC, CC, TO}
import javax.mail.Session
import javax.mail.internet.{InternetAddress, MimeBodyPart, MimeMessage, MimeMultipart}


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

	private var multipart = new MimeMultipart()

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
