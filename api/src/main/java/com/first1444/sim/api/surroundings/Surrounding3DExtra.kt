package com.first1444.sim.api.surroundings

import java.lang.Math.toDegrees
import java.lang.Math.toRadians

class Surrounding3DExtra
private constructor(
        val height: Double,
        val pitchRadians: Double,
        val rollRadians: Double
) {
    companion object {
        @JvmStatic
        fun fromRadians(height: Double, pitchRadians: Double, rollRadians: Double) = Surrounding3DExtra(height, pitchRadians, rollRadians)
        @JvmStatic
        fun fromDegrees(height: Double, pitchDegrees: Double, rollDegrees: Double) = Surrounding3DExtra(height, toRadians(pitchDegrees), toRadians(rollDegrees))
    }
    val pitchDegrees: Double = toDegrees(pitchRadians)
    val rollDegrees: Double = toDegrees(rollRadians)

    override fun toString(): String {
        return "Surrounding3DExtra(height=$height, pitchRadians=$pitchRadians, pitchDegrees=$pitchDegrees, rollRadians=$rollRadians, rollDegrees=$rollDegrees)"
    }
}
