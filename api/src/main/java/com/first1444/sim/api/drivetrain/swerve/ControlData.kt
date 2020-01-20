package com.first1444.sim.api.drivetrain.swerve

import com.first1444.sim.api.Vector2
import kotlin.math.abs


data class ControlData(
        val translation: Vector2,
        val turnAmount: Double,
        val speed: Double
) {
    companion object {
        private const val MAX_ONE = 1.000000000000001 // tested and got 1.0000000000000002 one time, so this will be fine
        val CONTROL_ZERO: ControlData = ControlData(Vector2.ZERO, 0.0, 0.0)
    }
    init {
        // 1.0000000000000002
        require(abs(translation.x) <= MAX_ONE) { "x must be in range [-1..1] x=${translation.x}" }
        require(abs(translation.y) <= MAX_ONE) { "y must be in range [-1..1] y=${translation.y}" }
        require(abs(turnAmount) <= MAX_ONE) { "turnAmount must be in range [-1..1] turnAmount=$turnAmount" }
        require(abs(speed) <= MAX_ONE) { "speed must be in range [-1..1] speed=$speed" }
    }
}
