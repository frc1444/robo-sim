package com.first1444.sim.api

import java.lang.Math.toDegrees
import java.lang.Math.toRadians
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.sin

class Rotation
private constructor(
        radians: Double,
        val cos: Double,
        val sin: Double
) {
    val radians: Double = halfMod(radians, Math.PI * 2)
    val degrees: Double = toDegrees(this.radians)

    companion object {
        @JvmField
        val ZERO = Rotation(0.0, 1.0, 0.0)

        @JvmStatic
        fun rotationRadians(radians: Double) = Rotation(radians, cos(radians), sin(radians))
        @JvmStatic
        fun rotationDegrees(degrees: Double) = rotationRadians(toRadians(degrees))

        @JvmStatic
        fun fromVector(x: Double, y: Double): Rotation {
            if(y == 0.0){
                return ZERO
            }
            val angle = atan2(y, x)
            val magnitude = hypot(x, y)
            if(magnitude <= 1e-6){
                return Rotation(angle, 1.0, 0.0)
            }
            return Rotation(angle, x / magnitude, y / magnitude)
        }
    }

    operator fun unaryMinus() = rotationRadians(-radians)
    operator fun unaryPlus() = this
    operator fun plus(other: Rotation): Rotation {
        val newCos = cos * other.cos - sin * other.sin
        val newSin = cos * other.sin + sin * other.cos
        return Rotation(atan2(newSin, newCos), newCos, newSin)
    }
    operator fun minus(other: Rotation): Rotation {
        val newCos = cos * -other.cos + sin * other.sin
        val newSin = cos * -other.sin - sin * other.cos
        return Rotation(atan2(newSin, newCos), newCos, newSin)
    }

    fun plusRadians(otherRadians: Double) = rotationRadians(radians + otherRadians)
    fun plusDegrees(otherDegrees: Double) = rotationDegrees(degrees + otherDegrees)
    fun minusRadians(otherRadians: Double) = rotationRadians(radians - otherRadians)
    fun minusDegrees(otherDegrees: Double) = rotationDegrees(degrees - otherDegrees)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Rotation

        if (radians != other.radians) return false

        return true
    }

    override fun hashCode(): Int {
        return radians.hashCode()
    }

    override fun toString(): String {
        return "Rotation(radians=%.16f, degrees=%.1f, cos=%.3f, sin=%.3f)".format(radians, degrees, cos, sin)
    }

}
