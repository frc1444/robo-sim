package com.first1444.sim.api.frc

abstract class IterativeRobotRunnable(
        modeGetter: () -> FrcMode
) : BasicRobotRunnable(modeGetter) {

    protected abstract fun robotPeriodic()

    protected abstract fun disabledInit()
    protected abstract fun disabledPeriodic()

    protected abstract fun autonomousInit()
    protected abstract fun autonomousPeriodic()

    protected abstract fun teleopInit()
    protected abstract fun teleopPeriodic()

    protected abstract fun testInit()
    protected abstract fun testPeriodic()

    override fun update(mode: FrcMode, previousMode: FrcMode?) {
        if(mode != previousMode){
            when(mode){
                FrcMode.DISABLED -> disabledInit()
                FrcMode.TELEOP -> teleopInit()
                FrcMode.AUTONOMOUS -> autonomousInit()
                FrcMode.TEST -> testInit()
            }
        }
        when(mode){
            FrcMode.DISABLED -> disabledPeriodic()
            FrcMode.TELEOP -> teleopPeriodic()
            FrcMode.AUTONOMOUS -> autonomousPeriodic()
            FrcMode.TEST -> testPeriodic()
        }
        robotPeriodic()
    }

}
