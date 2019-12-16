package com.first1444.sim.api

import java.lang.Math.toRadians
import java.text.DecimalFormat
import kotlin.math.*


class Vector2(
        val x: Double,
        val y: Double
) : Comparable<Vector2>{
    companion object {
        @JvmField val ZERO = Vector2(0.0, 0.0)
        @JvmField val ONE = Vector2(1.0, 1.0)
        @JvmField val X = Vector2(1.0, 0.0)
        @JvmField val Y = Vector2(0.0, 1.0)

        const val DEFAULT_EPSILON = 0.000_000_000_000_001 // 0.000_000_000_000_000_1

        private val FORMAT = DecimalFormat(" #0.0000000000000000;-#0.0000000000000000")
    }
    val magnitude: Double by lazy { hypot(x, y) }
    val magnitude2: Double = x * x + y * y
    val angle: Rotation2 by lazy { Rotation2.fromVector(this) }
    val angleRadians: Double get() = angle.radians
    val angleDegrees: Double get() = angle.degrees

    /**
     * @return The normalized [Vector2] or a [Vector2] with x=0 and y=0 if [magnitude] is 0
     */
    val normalized: Vector2
        get() {
            return Vector2(angle.cos, angle.sin)
        }

    @JvmOverloads
    fun epsilonEquals(other: Vector2, precision: Double = DEFAULT_EPSILON): Boolean {
        return (x - other.x).absoluteValue <= precision && (y - other.y).absoluteValue <= precision
    }
    fun distance2(otherX: Double, otherY: Double): Double {
        val x = this.x - otherX
        val y = this.y - otherY
        return x * x + y * y
    }
    fun distance2(other: Vector2): Double {
        return distance2(other.x, other.y)
    }
    fun distance(otherX: Double, otherY: Double): Double {
        return hypot(x - otherX, y - otherY)
    }
    fun distance(other: Vector2): Double {
        return distance(other.x, other.y)
    }
    operator fun compareTo(otherMagnitude: Double): Int {
        val magnitude2 = magnitude2
        val otherMagnitude2 = otherMagnitude * otherMagnitude
        return magnitude2.compareTo(otherMagnitude2)
    }
    override operator fun compareTo(other: Vector2): Int {
        val magnitude2 = magnitude2
        val otherMagnitude2 = other.magnitude2
        return magnitude2.compareTo(otherMagnitude2)
    }

    operator fun plus(vector: Vector2): Vector2{
        return Vector2(x + vector.x, y + vector.y)
    }
    fun plus(x: Double, y: Double): Vector2 {
        return Vector2(this.x + x, this.y + y)
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
    @JvmOverloads fun rotate(cos: Double, sin: Double, origin: Vector2 = ZERO): Vector2 {
        return Vector2(
                origin.x + cos * (x - origin.x) - sin * (y - origin.y),
                origin.y + sin * (x - origin.x) + cos * (y - origin.y)
        )
    }
    @JvmOverloads fun rotate(rotation: Rotation2, origin: Vector2 = ZERO): Vector2 {
        return rotate(rotation.cos, rotation.sin, origin)
    }
    @JvmOverloads fun rotateRadians(radians: Double, origin: Vector2 = ZERO): Vector2 {
        val cos = cos(radians)
        val sin = sin(radians)
        return rotate(cos, sin, origin)
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

    /** Keeps the y value and uses [x] as the new x value*/
    fun withX(x: Double) = Vector2(x, this.y)
    /** Keeps the x value and uses [y] as the new y value*/
    fun withY(y: Double) = Vector2(this.x, y)

    @JvmOverloads fun rotateDegrees(degrees: Double, origin: Vector2 = ZERO): Vector2 = rotateRadians(toRadians(degrees), origin)
    override fun toString(): String {
        return "Vector2(x=${FORMAT.format(x)}, y=${FORMAT.format(y)})"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vector2

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        return result
    }

}
