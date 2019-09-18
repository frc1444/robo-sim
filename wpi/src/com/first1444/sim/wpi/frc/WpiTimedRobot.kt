package com.first1444.sim.wpi.frc

import com.first1444.sim.api.RunnableCreator
import edu.wpi.first.wpilibj.TimedRobot

class WpiTimedRobot(
        private val runnableCreator: RunnableCreator,
        period: Double = kDefaultPeriod
) : TimedRobot(period) {

    init {
        runnableCreator.prematureInit()
    }

    private lateinit var runnable: Runnable

    override fun robotInit() {
        runnable = runnableCreator.createRunnable()
    }
    override fun robotPeriodic() {
        runnable.run()
    }
}
