package com.first1444.sim.wpi.frc

import com.first1444.sim.api.RobotRunnable
import com.first1444.sim.api.RunnableCreator
import edu.wpi.first.wpilibj.DriverStation
import edu.wpi.first.wpilibj.Watchdog

open class WatchdogRunnableCreator(
        private val runnableCreator: RunnableCreator,
        private val period: Double
) : RunnableCreator {
    protected val watchdog = Watchdog(period, ::printLoopOverrunMessage)

    override fun prematureInit() {
        runnableCreator.prematureInit()
    }

    override fun createRunnable(): RobotRunnable {
        return WatchdogRobotRunnable()
    }

    protected open fun printLoopOverrunMessage() {
        DriverStation.reportWarning("Loop time of " + period + "s overrun\n", false)
    }
    protected open fun onWatchdogDisable(){
        if(watchdog.isExpired){
            watchdog.printEpochs()
        }
    }

    private inner class WatchdogRobotRunnable : RobotRunnable {
        private val robotRunnable: RobotRunnable

        init {
            watchdog.reset()
            robotRunnable = runnableCreator.createRunnable()
            watchdog.addEpoch("RunnableCreator.createRunnable()")
            watchdog.disable()
            onWatchdogDisable()
        }

        override fun run() {
            watchdog.reset()
            robotRunnable.run()
            watchdog.addEpoch("RobotRunnable.run()")
            watchdog.disable()
            onWatchdogDisable()
        }

        override fun close() {
            robotRunnable.close()
        }

    }

}
