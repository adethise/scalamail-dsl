package mail

class MailExample extends MailTemplate {

	// setting the host
	// syntax;	[host <-- "<hostname>[:<port>]"]
	// default: localhost:2525
	host <-- "localhost:2525"

	// setting the emitter and recipients
	// syntax:	<emitter> [to <recipient>] [cc <recipient>] [bcc <recipient>]
	//          where recipient = <address> | (<address>[, <address>]...)
	// OR syntax: <emitter>.
	//                  to(<recipient>).
	//                  cc(<recipient>).
	//                  bcc(<recipient>)
	//          where recipient = <address> | <address>[, <address>]...
	"abcd@gmail.com" to "web@gmail.com"

	// setting the message's subject
	message subject
			"This is a subject"

	// adding attachments to the message
	message attachment
			("test-1.txt", "test-2.txt", "test-3.png")

	// setting the message's content (in plain text)
	message content
			"""
	  A note about the tests.

	  Dear fellow developpers, everything is going as planned.
	  See you in the Intel room.

	  TopDev1337
			"""

	// setting the message's content (in HTML)
	// Can make use of Escape.html
	message HTMLcontent
			"""<body>
	  <h1>A note about the tests.</h1>

	  <p>Dear fellow developpers, <i>everything</i> is going as planned.<p>
	  <p>See you in the Intel room.</p>

	  <p><a href="http://me.net/">
			""" + Escape.html("TopDev & co.") +
			"""</a></p>
	  </body>
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
		println("Message sending failed.")
	}

	on success {
		// if the message is successfully sent
		println("Message sent successfully.")
	}
}
