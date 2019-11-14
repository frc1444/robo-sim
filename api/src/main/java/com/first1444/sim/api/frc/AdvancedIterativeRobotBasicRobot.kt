package com.first1444.sim.api.frc


class AdvancedIterativeRobotBasicRobot(
        private val iterativeRobot: AdvancedIterativeRobot
) : BasicRobot {
    @Throws(Exception::class)
    override fun close() {
        iterativeRobot.close()
    }

    override fun update(mode: FrcMode, previousMode: FrcMode?) {
        if(mode != previousMode){
            when(mode){
                FrcMode.DISABLED -> iterativeRobot.disabledInit(previousMode)
                FrcMode.TELEOP -> iterativeRobot.teleopInit()
                FrcMode.AUTONOMOUS -> iterativeRobot.autonomousInit()
                FrcMode.TEST -> iterativeRobot.testInit()
            }
        }
        when(mode){
            FrcMode.DISABLED -> iterativeRobot.disabledPeriodic()
            FrcMode.TELEOP -> iterativeRobot.teleopPeriodic()
            FrcMode.AUTONOMOUS -> iterativeRobot.autonomousPeriodic()
            FrcMode.TEST -> iterativeRobot.testPeriodic()
        }
        iterativeRobot.robotPeriodic()
    }

}
