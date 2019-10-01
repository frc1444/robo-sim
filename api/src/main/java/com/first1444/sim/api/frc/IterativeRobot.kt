package com.first1444.sim.api.frc

interface IterativeRobot {

    fun robotPeriodic()

    fun disabledInit()
    fun disabledPeriodic()

    fun autonomousInit()
    fun autonomousPeriodic()

    fun teleopInit()
    fun teleopPeriodic()

    fun testInit()
    fun testPeriodic()
}
