package com.first1444.sim.api

import java.lang.Math.toDegrees
import java.lang.Math.toRadians
import java.text.DecimalFormat
import kotlin.math.*


data class Vector2(
        val x: Double,
        val y: Double
) {
    companion object {
        @JvmField
        val ZERO = Vector2(0.0, 0.0)
        @JvmField
        val ONE = Vector2(1.0, 1.0)

        const val DEFAULT_EPSILON = 0.000_000_000_000_001 // 0.000_000_000_000_000_1

        private val FORMAT = DecimalFormat(" #0.0000000000000000;-#0.0000000000000000")
    }
    val magnitude: Double by lazy { hypot(x, y) }
    val angleRadians: Double by lazy { atan2(y, x) }
    val angleDegrees: Double
        get() = toDegrees(angleRadians)

    val normalized: Vector2
        get() = Vector2(x / magnitude, y / magnitude)

    @JvmOverloads
    fun epsilonEquals(other: Vector2, precision: Double = DEFAULT_EPSILON): Boolean {
        return (x - other.x).absoluteValue <= precision && (y - other.y).absoluteValue <= precision
    }

    operator fun plus(vector: Vector2): Vector2{
        return Vector2(x + vector.x, y + vector.y)
    }
    fun plus(x: Double, y: Double): Vector2 {
        return Vector2(this.x - x, this.y - y)
    }
    operator fun minus(vector: Vector2): Vector2{
        return Vector2(x - vector.x, y - vector.y)
    }
    fun minus(x: Double, y: Double): Vector2 {
        return Vector2(this.x - x, this.y - y)
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
    fun times(x: Double, y: Double): Vector2{
        return Vector2(this.x * x, this.y * y)
    }
    operator fun div(vector: Vector2): Vector2 {
        return Vector2(x / vector.x, y / vector.y)
    }
    operator fun div(value: Double): Vector2{
        return Vector2(x / value, y / value)
    }
    fun div(x: Double, y: Double): Vector2 {
        return Vector2(this.x / x, this.y / y)
    }
    @JvmOverloads fun rotateRadians(radians: Double, origin: Vector2 = ZERO): Vector2 {
        val cos = cos(radians)
        val sin = sin(radians)
        return Vector2(
                origin.x + cos * (x - origin.x) - sin * (y - origin.y),
                origin.y + sin * (x - origin.x) + cos * (y - origin.y)
        )
    }

    /**
     * @param amount The number of times to rotate 90 degrees in a certain direction. A positive value is counter clockwise, a negative clockwise, zero unchanged.
     */
    fun rotate90(amount: Int): Vector2 = when(val value = mod(amount, 4)){
        0 -> this
        1 -> Vector2(-y, x) // 90
        2 -> Vector2(-x, -y) // 180
        3 -> Vector2(y, -x) // 270
        else -> error("modulo of 4 of amount=$amount gave bad value=$value")
    }
    @JvmOverloads fun rotateDegrees(degrees: Double, origin: Vector2 = ZERO): Vector2 = rotateRadians(toRadians(degrees), origin)
    override fun toString(): String {
        return "Vector2(x=${FORMAT.format(x)}, y=${FORMAT.format(y)})"
    }
}
