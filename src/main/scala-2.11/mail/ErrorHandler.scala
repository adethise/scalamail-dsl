package mail

/**
  * Class to create error listeners
  */
class ErrorHandler() {

	private var listeners: List[() => Unit] = List()

	def activate(): Unit = listeners.foreach(l => l())

	def error(b: => Unit): Unit = {
		listeners = (() => b) :: listeners
	}

}
