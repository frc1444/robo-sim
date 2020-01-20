package com.first1444.sim.wpi.frc

import com.first1444.sim.api.RobotRunnable
import com.first1444.sim.api.RunnableCreator
import edu.wpi.first.hal.FRCNetComm
import edu.wpi.first.hal.HAL
import edu.wpi.first.hal.NotifierJNI
import edu.wpi.first.wpilibj.DriverStation
import edu.wpi.first.wpilibj.RobotBase
import edu.wpi.first.wpilibj.RobotController
import edu.wpi.first.wpilibj.Watchdog

open class RoboSimRobot
constructor(
        private val runnableCreator: RunnableCreator,
        private val period: Double
) : RobotBase() {
    private val notifier = NotifierJNI.initializeNotifier()
    private var expirationTime = 0.0

    @Deprecated("Define the default value yourself! This will be removed")
    constructor(runnableCreator: RunnableCreator) : this(runnableCreator, 0.02)

    init {
        NotifierJNI.setNotifierName(notifier, "RoboSimRobot")
        HAL.report(FRCNetComm.tResourceType.kResourceType_Framework, FRCNetComm.tInstances.kFramework_Timed)
        runnableCreator.prematureInit()
    }

    override fun startCompetition() {
        val robotRunnable = runnableCreator.createRunnable()

        // Tell the DS that the robot is ready to be enabled
        HAL.observeUserProgramStarting()

        expirationTime = RobotController.getFPGATime() * 1e-6 + period
        updateAlarm()

        // Loop forever, calling the appropriate mode-dependent function
        while (true) {
            val curTime = NotifierJNI.waitForNotifierAlarm(notifier)
            if (curTime == 0L) {
                break
            }

            expirationTime += period
            updateAlarm()

            loop(robotRunnable)
        }
    }

    override fun endCompetition() {
        NotifierJNI.stopNotifier(notifier)
    }

    private fun loop(robotRunnable: RobotRunnable){
        when {
            isDisabled -> HAL.observeUserProgramDisabled()
            isAutonomous -> HAL.observeUserProgramAutonomous()
            isOperatorControl -> HAL.observeUserProgramTeleop()
            else -> HAL.observeUserProgramTest()
        }
        robotRunnable.run()
    }

    /** Although there isn't an override, this still overrides the finalize function*/
    protected fun finalize() {
        NotifierJNI.stopNotifier(notifier)
        NotifierJNI.cleanNotifier(notifier)
    }

    /**
     * Update the alarm hardware to reflect the next alarm.
     */
    private fun updateAlarm() {
        NotifierJNI.updateNotifierAlarm(notifier, (expirationTime * 1e6).toLong())
    }
}
