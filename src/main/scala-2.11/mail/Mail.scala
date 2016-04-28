package mail

class Mail extends MailTemplate {

	host("localhost:2525")

	"saber@fate.net" to("archer@fate.net", "rider@fate.net") cc "shirou@fate.net"

	message subject
			"Holy Grail War"

	message attachment
			("test.txt", "test2.txt", "image.png")

	message content
			"""
	  <h1>A note about the war</h1>

	  <p>Dear fellow heroes, everything is going as planned.<p>
	  <p>See you on the battlefield.</p>

	  <p>Saber</p>
			"""

}