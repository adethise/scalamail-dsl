package mail

class Mail extends MailTemplate {

	host <-- "localhost:2525"

	"saber@fate.net" to("archer@fate.net", "rider@fate.net") cc "shirou@fate.net"

	message subject
			"Holy Grail War"

	message attachment
			("test-1.txt", "test-2.txt", "test-3.png")

	message content
			"""
	  A note about the war.

	  Dear fellow heroes, everything is going as planned.
	  See you on the battlefield.

	  Saber
			"""

	message HTMLcontent
			"""
	  <body>

	  <h1>A note about the war</h1>

	  <p>Dear fellow heroes, everything is going as planned.<p>
	  <p>See you on the battlefield.</p>

	  <p>Saber</p>

	  </body>
			"""


	on error {
		println("Bad end.")
	}

	on error {
		println("It's all Kirei's fault.")
		// this.send() # JUST KIDDING DON'T DO THIS (infinite loop !)
		// 1 / 0       # Don't do this either
	}

	on success {
		println("Good End.")
	}
}