package com.first1444.sim.api

import java.lang.Math.toDegrees
import java.lang.Math.toRadians
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.sin


data class Vector2(
        val x: Double,
        val y: Double
) {
    companion object {
        @JvmField
        val ZERO = Vector2(0.0, 0.0)
    }
    val magnitude: Double by lazy { hypot(x, y) }
    val angleRadians: Double by lazy { atan2(y, x) }
    val angleDegrees: Double
        get() = toDegrees(angleRadians)

    operator fun plus(vector: Vector2): Vector2{
        return Vector2(x + vector.x, y + vector.y)
    }
    operator fun minus(vector: Vector2): Vector2{
        return Vector2(x - vector.x, y - vector.y)
    }
    operator fun unaryMinus(): Vector2 {
        return Vector2(-x, -y)
    }
    operator fun unaryPlus(): Vector2 {
        return this
    }
    operator fun times(vector: Vector2): Vector2{
        return Vector2(x * vector.x, y * vector.y)
    }
    operator fun times(scaleValue: Double): Vector2 {
        return Vector2(x * scaleValue, y * scaleValue)
    }
    operator fun div(vector: Vector2): Vector2 {
        return Vector2(x / vector.x, y / vector.y)
    }
    operator fun div(value: Double): Vector2{
        return Vector2(x / value, y / value)
    }
    @JvmOverloads fun rotateRadians(radians: Double, origin: Vector2 = ZERO): Vector2 {
        val cos = cos(radians)
        val sin = sin(radians)
        return Vector2(
                origin.x + cos * (x - origin.x) - sin * (y - origin.y),
                origin.y + sin * (x - origin.x) + cos * (y - origin.y)
        )
    }
    @JvmOverloads fun rotateDegrees(degrees: Double, origin: Vector2 = ZERO): Vector2 = rotateRadians(toRadians(degrees))
    override fun toString(): String {
        return "Vector2(x=$x, y=$y)"
    }
}
