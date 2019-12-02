package com.first1444.sim.api.frc.sim

import com.first1444.sim.api.frc.FrcLogger
import java.io.PrintStream

class PrintStreamFrcLogger(
        private val warningStream: PrintStream,
        private val errorStream: PrintStream
) : FrcLogger {
    private fun getTraceString(stackTrace: Array<StackTraceElement>): String {
        val traceString = StringBuilder()
        for (element in stackTrace) {
            val loc = element.toString()
            traceString.append("\tat ").append(loc).append('\n')
        }
        return traceString.toString()
    }
    override fun reportWarning(error: String, printTrace: Boolean) {
        warningStream.println(error)
        warningStream.println(getTraceString(Throwable().stackTrace))
    }

    override fun reportWarning(error: String, stackTrace: Array<StackTraceElement>) {
        warningStream.println(error)
        warningStream.println(getTraceString(stackTrace))
    }

    override fun reportError(error: String, printTrace: Boolean) {
        errorStream.println(error)
        errorStream.println(getTraceString(Throwable().stackTrace))
    }

    override fun reportError(error: String, stackTrace: Array<StackTraceElement>) {
        errorStream.println(error)
        errorStream.println(getTraceString(stackTrace))
    }

}
