package com.first1444.sim.api.output


interface SpeedOutput {
    fun setSpeed(speed: Double)
}
interface MotorSpeedOutput : SpeedOutput, MotorOutput
