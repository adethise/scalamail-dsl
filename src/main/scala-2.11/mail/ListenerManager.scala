package mail

import scala.collection.mutable.ListBuffer

/**
  * Class to create error listeners
  */
class ListenerManager() {

	private val errors: ListBuffer[() => Unit] = ListBuffer()
	private val successes: ListBuffer[() => Unit] = ListBuffer()

	def errored(): Unit = errors.foreach(l => l())

	def succeeded(): Unit = successes.foreach(l => l())

	def error(b: => Unit): Unit = {
		errors append (() => b)
	}

	def success(b: => Unit): Unit = {
		successes append (() => b)
	}

}
