package com.first1444.sim.wpi.frc

import com.first1444.sim.api.RobotRunnable
import com.first1444.sim.api.RunnableCreator
import edu.wpi.first.wpilibj.TimedRobot

open class WpiTimedRobot @JvmOverloads constructor(
        private val runnableCreator: RunnableCreator,
        period: Double = kDefaultPeriod
) : TimedRobot(period) {

    init {
        runnableCreator.prematureInit()
    }

    private lateinit var runnable: RobotRunnable

    override fun robotInit() {
        runnable = runnableCreator.createRunnable()
    }
    override fun robotPeriodic() {
        runnable.run()
    }
    override fun startCompetition() {
        super.startCompetition()
        runnable.close()
    }
    override fun disabledInit() {}
    override fun disabledPeriodic() {}
    override fun autonomousInit() {}
    override fun autonomousPeriodic() {}
    override fun teleopInit() {}
    override fun teleopPeriodic() {}
    override fun testInit() {}
    override fun testPeriodic() {}
}
