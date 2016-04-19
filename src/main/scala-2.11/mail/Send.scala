package mail

/**
  * Sender class for Mail.
  * This class will simply create an instance of Mail based on
  * the content of the Mail.scala DSL file, and send it.
  *
  * This is the class that must be run to send the message.
  */
object Send {

  def main(args: Array[String]) {
    val mail = new Mail
    mail.send()
  }

}
