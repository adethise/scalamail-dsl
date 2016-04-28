package mail

import scala.collection.mutable.ListBuffer

/**
  * Class to create error listeners
  */
class ErrorHandler() {

	private val listeners: ListBuffer[() => Unit] = ListBuffer()

	def activate(): Unit = listeners.foreach(l => l())

	def error(b: => Unit): Unit = {
		listeners append (() => b)
	}

}
