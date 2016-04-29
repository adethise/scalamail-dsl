package utils

import scala.collection.mutable.ListBuffer

/**
  * Class to create error listeners
  */
trait ListenerManager {

	protected val on = new {

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
}
