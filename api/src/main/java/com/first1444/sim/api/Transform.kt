package com.first1444.sim.api

import java.lang.Math.toRadians

class Transform
private constructor(
        val position: Vector2,
        val rotation: Rotation
) {
    val rotationRadians: Double get() = rotation.radians
    val rotationDegrees: Double get() = rotation.degrees

    companion object {
        @JvmField
        val ZERO = Transform(Vector2.ZERO, Rotation.ZERO)

        /**
         * Creates a [Transform] using [rotationRadians] as the rotation
         * @return A new transform with position and rotationRadians
         */
        @JvmStatic fun transformRadians(position: Vector2, rotationRadians: Double): Transform = Transform(position, Rotation.rotationRadians(rotationRadians))
        /**
         * Creates a [Transform] using [rotationRadians] as the rotation
         * @return A new transform with x and y position and rotationRadians
         */
        @JvmStatic fun transformRadians(x: Double, y: Double, rotationRadians: Double): Transform = transformRadians(Vector2(x, y), rotationRadians)
        /**
         * Creates a [Transform] using [rotationDegrees] as the rotation
         * @return A new transform with position and rotationDegrees
         */
        @JvmStatic fun transformDegrees(position: Vector2, rotationDegrees: Double): Transform = transformRadians(position, toRadians(rotationDegrees))
        /**
         * Creates a [Transform] using [rotationDegrees] as the rotation
         * @return A new transform with x and y position and rotationDegrees
         */
        @JvmStatic fun transformDegrees(x: Double, y: Double, rotationDegrees: Double): Transform = transformDegrees(Vector2(x, y), rotationDegrees)

        @JvmStatic fun transform(position: Vector2, rotation: Vector2): Transform = transformRadians(position, rotation.angleRadians)

    }

    val x: Double
        get() = position.x
    val y: Double
        get() = position.y

    fun rotateRadians(angle: Double): Transform {
        return transformRadians(position.rotateRadians(angle), rotationRadians + angle)
    }
    fun rotateDegrees(angle: Double): Transform {
        return transformDegrees(position.rotateDegrees(angle), rotationDegrees + angle)
    }

    operator fun unaryMinus(): Transform {
        return Transform(-position, rotation.plusRadians(Math.PI))
    }
    operator fun plus(vector: Vector2): Transform {
        return Transform(position + vector, rotation)
    }
    fun plus(x: Double, y: Double): Transform{
        return Transform(position.plus(x, y), rotation)
    }
    operator fun minus(vector: Vector2): Transform {
        return Transform(position - vector, rotation)
    }
    fun minus(x: Double, y: Double): Transform {
        return Transform(position.minus(x, y), rotation)
    }

    /**
     * NOTE: [Transform] always equals [Transform.reversed].[reversed]
     *
     * Rounding errors may be made so you should use [epsilonEquals] to check equality
     * @return A new [Transform] representing this [Transform] relative to the position and rotation represented by this [Transform]
     */
    val reversed: Transform
        get() {
            val rotation = -rotation
            val x = this.x
            val y = this.y
            return Transform(Vector2(
                    -(x * rotation.cos - y * rotation.sin),
                    -(x * rotation.sin + y * rotation.cos)
            ), rotation)
        }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Transform

        if (position != other.position) return false
        if (rotationRadians != other.rotationRadians) return false
        return true
    }

    fun epsilonEquals(other: Transform): Boolean {
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
