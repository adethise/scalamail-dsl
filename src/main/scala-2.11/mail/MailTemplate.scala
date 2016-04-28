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

	protected var message: ConfigurableMessage = null
	protected var session: Session = null

	protected var on: ListenerManager = new ListenerManager()

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

		// create, store and return the message
		message = if (session == null) {
			new ConfigurableMessage(emitter)
		} else {
			new ConfigurableMessage(emitter, session)
		}

		message
	}

	/**
	  * Define the SMTP server parameters.
	  *
	  * @param name A string hostname:port
	  */
	def host(name: String) = {
		val hostport = name split ":"
		val hostname = if (hostport.nonEmpty) hostport(0) else "localhost"
		val port = if (hostport.tail.nonEmpty) hostport(1) else "2525"
		val properties: Properties = System.getProperties
		properties.setProperty("mail.smtp.host", hostname)
		properties.setProperty("mail.smtp.port", port)
		session = Session.getDefaultInstance(properties)
	}

	/**
	  * Send the mail.
	  */
	def send() = {
		try {
			Transport.send(message lowLevel())
			on.succeeded()
		}
		catch {
			case e: Exception => on.errored()
		}
	}

	def getLowLevelMessage = message lowLevel()

	def setLowLevelSession(session: Session) = this.session = session
}
