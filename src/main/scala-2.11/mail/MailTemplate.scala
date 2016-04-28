package mail

import java.util.Properties
import javax.mail.{Session, Transport}

/**
  * DSL Mail Template
  * This class is the template extended by a DSL Mail.
  * It includes attributes and method definitions.
  *
  * @author Arnaud Dethise
  *         Jonathan Powell
  * @version 1.2
  */
abstract class MailTemplate {

	/* Class attributes */

	// SMTP server information
	var (hostname, port) = ("localhost", "2525")

	// Message
	protected var message: ConfigurableMessage = null
	protected var session: Session = null

	protected var on: ErrorHandler = new ErrorHandler()

	/**
	  * Implicit conversion of String to ConfigurableMessage
	  * Is called by writing a String containing the emitter.
	  * Should be followed by the destinators.
	  *
	  * Note : the server must have been configured before this call (see host).
	  *
	  * @param emitter The message emitter. Supports only one
	  * @return The message itself, so that other methods can be called on it
	  */
	implicit def stringToMessage(emitter: String): ConfigurableMessage = {

		if (session == null) {
			// configure session properties
			val properties: Properties = System.getProperties
			properties.setProperty("mail.smtp.host", hostname)
			properties.setProperty("mail.smtp.port", port)
			val session: Session = Session.getDefaultInstance(properties)
		}

		// create, store and return the message
		message = new ConfigurableMessage(emitter, session)
		message
	}

	/**
	  * Define the SMTP server parameters.
	  *
	  * @param name A string hostname:port
	  */
	def host(name: String) = {
		val hostport = name split ":"
		hostname = hostport(0)
		port = hostport(1)
	}

	/**
	  * Send the mail.
	  */
	def send() = {
		try {
			Transport.send(message lowLevel())
			println("Sent message successfully....")
		}
		catch {
			case e: Exception => on.activate()
		}
	}
}
