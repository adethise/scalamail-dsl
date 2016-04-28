package mail

class MailExample extends MailTemplate {

	// setting the host
	// syntax;	[host("<hostname>:<port>")]
	// default: localhost:2525
	host("localhost:2525")

	// setting the emitter and recipients
	// syntax:	<emitter> [to <recipient>] [cc <recipient>] [bcc <recipient>]
	//					where recipient = <address> | (<address>[, <address>]...)
	"abcd@gmail.com" to "web@gmail.com"

	// setting the message's subject
	message subject
			"Holy Grail War"

	// adding attachments to the message
	message attachment
			("file1", "file2")

	// setting the message's content (in plain text)
	message content
			"""
	  A note about the war.

	  Dear fellow heroes, everything is going as planned.
	  See you on the battlefield.

	  Saber
			"""

	// setting the message's content (in HTML)
	message HTMLcontent
			"""
	  <h1>A note about the war</h1>

	  <p>Dear fellow heroes, everything is going as planned.<p>
	  <p>See you on the battlefield.</p>

	  <p>Saber</p>
			"""

	/* low level configuration :
	 * message:
	 *		getLowLevelMessage
	 *	=> allows to modify the MimeMessage before sending
	 * session:
	 *		setLowLevelSession
	 *  => allows to set the session with more than just host:port
	 */

	// Adding event listeners :
	on error {
		// if the message failed to be sent
		println("Bad end.")
	}

	on success {
		// if the message is successfully sent
		println("Good End.")
	}
}
