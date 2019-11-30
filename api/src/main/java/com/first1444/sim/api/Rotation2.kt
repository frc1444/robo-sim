package com.first1444.sim.api

import java.lang.Math.toDegrees
import java.lang.Math.toRadians
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.sin

class Rotation2
private constructor(
        radians: Double,
        val cos: Double,
        val sin: Double
) {
    val radians: Double = halfMod(radians, Math.PI * 2)
    val degrees: Double = toDegrees(this.radians)
    val tan: Double get() = sin / cos

    companion object {
        @JvmField
        val ZERO = Rotation2(0.0, 1.0, 0.0)
        /** pi radians or 180 degrees*/
        @JvmField
        val DEG_180 = Rotation2(Math.PI, -1.0, 0.0)
        @JvmField
        val DEG_90 = Rotation2(Math.PI / 2.0, 0.0, 1.0)
        @JvmField
        val DEG_45 = fromRadians(Math.PI / 4.0)
        /** 270 degrees or -90 degrees */
        @JvmField
        val DEG_270 = Rotation2(-Math.PI / 2.0, 0.0, -1.0)

        @JvmStatic
        fun fromRadians(radians: Double) = Rotation2(radians, cos(radians), sin(radians))
        @JvmStatic
        fun fromDegrees(degrees: Double) = fromRadians(toRadians(degrees))

        @JvmStatic
        fun fromVector(vector: Vector2): Rotation2 = fromVector(vector.x, vector.y)
        @JvmStatic
        fun fromVector(x: Double, y: Double): Rotation2 {
            if(y == 0.0){
                if(x < 0){
                    return DEG_180
                }
                return ZERO
            }
            val angle = atan2(y, x)
            val magnitude = hypot(x, y)
            if(magnitude <= 1e-6){
                return ZERO
            }
            return Rotation2(angle, x / magnitude, y / magnitude)
        }
    }

    operator fun unaryMinus() = fromRadians(-radians)
    operator fun unaryPlus() = this
    operator fun plus(other: Rotation2): Rotation2 {
        val newCos = cos * other.cos - sin * other.sin
        val newSin = cos * other.sin + sin * other.cos
        return Rotation2(atan2(newSin, newCos), newCos, newSin)
    }
    operator fun minus(other: Rotation2): Rotation2 {
        val newCos = cos * -other.cos + sin * other.sin
        val newSin = cos * -other.sin - sin * other.cos
        return Rotation2(atan2(newSin, newCos), newCos, newSin)
    }

    fun plusRadians(otherRadians: Double) = fromRadians(radians + otherRadians)
    fun plusDegrees(otherDegrees: Double) = fromDegrees(degrees + otherDegrees)
    fun minusRadians(otherRadians: Double) = fromRadians(radians - otherRadians)
    fun minusDegrees(otherDegrees: Double) = fromDegrees(degrees - otherDegrees)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Rotation2

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
