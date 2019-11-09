@file:JvmName("Transforms")
package com.first1444.sim.api

import java.lang.Math.toDegrees
import java.lang.Math.toRadians
import kotlin.math.cos
import kotlin.math.sin

class Transform
private constructor(
        val position: Vector2,
        val rotationRadians: Double,
        val rotationDegrees: Double
) {
    companion object {
        @JvmField
        val ZERO = Transform(Vector2.ZERO, 0.0, 0.0)

        /**
         * Creates a [Transform] using [rotationRadians] as the rotation
         * @return A new transform with position and rotationRadians
         */
        @JvmStatic fun transformRadians(position: Vector2, rotationRadians: Double): Transform =
                Transform(position, rotationRadians, toDegrees(rotationRadians))
        /**
         * Creates a [Transform] using [rotationRadians] as the rotation
         * @return A new transform with x and y position and rotationRadians
         */
        @JvmStatic fun transformRadians(x: Double, y: Double, rotationRadians: Double): Transform = transformRadians(Vector2(x, y), rotationRadians)
        /**
         * Creates a [Transform] using [rotationDegrees] as the rotation
         * @return A new transform with position and rotationDegrees
         */
        @JvmStatic fun transformDegrees(position: Vector2, rotationDegrees: Double): Transform =
                Transform(position, toRadians(rotationDegrees), rotationDegrees)
        /**
         * Creates a [Transform] using [rotationDegrees] as the rotation
         * @return A new transform with x and y position and rotationDegrees
         */
        @JvmStatic fun transformDegrees(x: Double, y: Double, rotationDegrees: Double): Transform = Transform(Vector2(x, y), toRadians(rotationDegrees), rotationDegrees)

        @JvmStatic fun transform(position: Vector2, rotation: Vector2): Transform {
            return transformRadians(position, rotation.angleRadians)
        }

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
        return Transform(-position, Math.PI + rotationRadians, 180 + rotationDegrees)
    }

    /**
     * NOTE: [Transform] always equals [Transform.reversed].[reversed]
     *
     * Rounding errors may be made so you should use [epsilonEquals] to check equality
     * @return A new [Transform] representing this [Transform] relative to the position and rotation represented by this [Transform]
     */
    val reversed: Transform
        get() {
            val rotationRadians = -rotationRadians
            val rotationDegrees = -rotationDegrees
            val sin = sin(rotationRadians)
            val cos = cos(rotationRadians)
            val x = this.x
            val y = this.y
            return Transform(Vector2(
                    -(x * cos - y * sin),
                    -(x * sin + y * cos)
            ), rotationRadians, rotationDegrees)
        }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Transform

        if (position != other.position) return false
        if (rotationRadians != other.rotationRadians) return false
        // we don't need to check rotationDegrees

        return true
    }

    fun epsilonEquals(other: Transform): Boolean {
        return position.epsilonEquals(other.position) && rotationRadians == other.rotationRadians
    }

    override fun hashCode(): Int {
        var result = position.hashCode()
        result = 31 * result + rotationRadians.hashCode()
        result = 31 * result + rotationDegrees.hashCode()
        return result
    }

    override fun toString(): String {
        return "Transform(position=$position, rotationRadians=$rotationRadians, rotationDegrees=$rotationDegrees)"
    }

}
