package com.first1444.sim.api.frc

interface FrcLogger {
    fun reportWarning(error: String, printTrace: Boolean)
    fun reportWarning(error: String, stackTrace: Array<StackTraceElement>)
    fun reportError(error: String, printTrace: Boolean)
    fun reportError(error: String, stackTrace: Array<StackTraceElement>)
}
