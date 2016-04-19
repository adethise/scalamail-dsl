package mail

/**
  * Created by bainos on 19/04/16.
  */
object Send {

  def main(args: Array[String]) {
    val mail = new Mail
    mail.send()
  }

}
