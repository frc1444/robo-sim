package com.first1444.sim.api

import java.lang.Math.toRadians

class Transform2(
        val position: Vector2,
        val rotation: Rotation2
) {
    constructor(x: Double, y: Double, rotation: Rotation2) : this(Vector2(x, y), rotation)
    companion object {
        @JvmField
        val ZERO = Transform2(Vector2.ZERO, Rotation2.ZERO)

        /**
         * Creates a [Transform2] using [rotationRadians] as the rotation
         * @return A new transform with position and rotationRadians
         */
        @JvmStatic fun fromRadians(position: Vector2, rotationRadians: Double): Transform2 = Transform2(position, Rotation2.fromRadians(rotationRadians))
        /**
         * Creates a [Transform2] using [rotationRadians] as the rotation
         * @return A new transform with x and y position and rotationRadians
         */
        @JvmStatic fun fromRadians(x: Double, y: Double, rotationRadians: Double): Transform2 = fromRadians(Vector2(x, y), rotationRadians)
        /**
         * Creates a [Transform2] using [rotationDegrees] as the rotation
         * @return A new transform with position and rotationDegrees
         */
        @JvmStatic fun fromDegrees(position: Vector2, rotationDegrees: Double): Transform2 = fromRadians(position, toRadians(rotationDegrees))
        /**
         * Creates a [Transform2] using [rotationDegrees] as the rotation
         * @return A new transform with x and y position and rotationDegrees
         */
        @JvmStatic fun fromDegrees(x: Double, y: Double, rotationDegrees: Double): Transform2 = fromDegrees(Vector2(x, y), rotationDegrees)

        @JvmStatic fun fromVector(position: Vector2, rotation: Vector2): Transform2 = Transform2(position, Rotation2.fromVector(rotation))
    }

    val rotationRadians: Double get() = rotation.radians
    val rotationDegrees: Double get() = rotation.degrees

    val x: Double get() = position.x
    val y: Double get() = position.y

    fun rotateRadians(angle: Double): Transform2 {
        return fromRadians(position.rotateRadians(angle), rotationRadians + angle)
    }
    fun rotateDegrees(angle: Double): Transform2 {
        return fromDegrees(position.rotateDegrees(angle), rotationDegrees + angle)
    }
    fun rotate(angle: Rotation2): Transform2 {
        return Transform2(position.rotate(angle), rotation + angle)
    }
    fun rotateClockwise(angle: Rotation2): Transform2 {
        return Transform2(position.rotateClockwise(angle), rotation - angle)
    }

    operator fun unaryMinus(): Transform2 {
        return Transform2(-position, rotation.plusRadians(Math.PI))
    }
    operator fun plus(vector: Vector2): Transform2 {
        return Transform2(position + vector, rotation)
    }
    fun plus(x: Double, y: Double): Transform2{
        return Transform2(position.plus(x, y), rotation)
    }
    operator fun minus(vector: Vector2): Transform2 {
        return Transform2(position - vector, rotation)
    }
    fun minus(x: Double, y: Double): Transform2 {
        return Transform2(position.minus(x, y), rotation)
    }
    fun withX(x: Double) = Transform2(x, this.y, this.rotation)
    fun withY(y: Double) = Transform2(this.x, y, this.rotation)
    fun withPosition(position: Vector2) = Transform2(position, this.rotation)
    fun withRotation(rotation: Rotation2) = Transform2(this.position, rotation)

    /**
     * NOTE: [Transform2] always equals [Transform2.reversed].[reversed]
     *
     * Rounding errors may be made so you should use [epsilonEquals] to check equality
     * @return A new [Transform2] representing this [Transform2] relative to the position and rotation represented by this [Transform2]
     */
    val reversed: Transform2
        get() {
            val rotation = -rotation
            val x = this.x
            val y = this.y
            return Transform2(Vector2(
                    -(x * rotation.cos - y * rotation.sin),
                    -(x * rotation.sin + y * rotation.cos)
            ), rotation)
        }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Transform2

        if (position != other.position) return false
        if (rotation != other.rotation) return false
        return true
    }

    fun epsilonEquals(other: Transform2): Boolean {
        return position.epsilonEquals(other.position) && rotation == rotation
    }

    override fun hashCode(): Int {
        var result = position.hashCode()
        result = 31 * result + rotation.hashCode()
        return result
    }

    override fun toString(): String {
        return "Transform(position=$position, rotation=$rotation)"
    }

}
