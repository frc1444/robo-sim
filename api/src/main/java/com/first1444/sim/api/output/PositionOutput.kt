package com.first1444.sim.api.output

interface PositionOutput {
    fun setPosition(position: Double)
}
interface MotorPositionOutput : PositionOutput, MotorOutput
