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

	message subject
			"Holy Grail War"

	message content
			"""
			 <h1>A note about the war</h1>

			 <p>Dear fellow heroes, everything is going as planned.<p>
			 <p>See you on the battlefield.</p>
			 <p>Saber</p>
			"""

	/* low level configuration :
	 * message:
	 *		val lowLevelMessage: MimeMessage = message.lowLevel()
	 *		must have set either the emitter / recipients or a manual session
	 * session:
	 *		session: javax.mail.Session = new Session(...)
	 */
}
