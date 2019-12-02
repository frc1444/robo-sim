package com.first1444.sim.wpi.frc

import com.first1444.sim.api.frc.FrcLogger
import edu.wpi.first.wpilibj.DriverStation

object DriverStationLogger : FrcLogger {
    override fun reportWarning(error: String, printTrace: Boolean) {
        DriverStation.reportWarning(error, printTrace)
    }

    override fun reportWarning(error: String, stackTrace: Array<StackTraceElement>) {
        DriverStation.reportWarning(error, stackTrace)
    }

    override fun reportError(error: String, printTrace: Boolean) {
        DriverStation.reportError(error, printTrace)
    }

    override fun reportError(error: String, stackTrace: Array<StackTraceElement>) {
        DriverStation.reportError(error, stackTrace)
    }
}
