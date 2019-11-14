package com.first1444.sim.api.frc

interface BaseIterativeRobot : AutoCloseable {

    fun robotPeriodic()

    fun disabledPeriodic()

    fun autonomousInit()
    fun autonomousPeriodic()

    fun teleopInit()
    fun teleopPeriodic()

    fun testInit()
    fun testPeriodic()
}
