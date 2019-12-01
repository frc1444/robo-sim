package com.first1444.sim.api.surroundings

import com.first1444.sim.api.Rotation2
import java.lang.Math.toDegrees
import java.lang.Math.toRadians

class Surrounding3DExtra(
        val height: Double,
        val pitch: Rotation2,
        val roll: Rotation2
) {
    companion object {
        @JvmStatic
        fun fromRadians(height: Double, pitchRadians: Double, rollRadians: Double) = Surrounding3DExtra(height, Rotation2.fromRadians(pitchRadians), Rotation2.fromRadians(rollRadians))
        @JvmStatic
        fun fromDegrees(height: Double, pitchDegrees: Double, rollDegrees: Double) = Surrounding3DExtra(height, Rotation2.fromDegrees(pitchDegrees), Rotation2.fromDegrees(rollDegrees))
    }
    val pitchRadians: Double get() = pitch.radians
    val rollRadians: Double get() = roll.radians
    val pitchDegrees: Double get() = pitch.degrees
    val rollDegrees: Double get() = roll.degrees

    override fun toString(): String {
        return "Surrounding3DExtra(height=$height, pitch=$pitch, roll=$roll)"
    }
}
