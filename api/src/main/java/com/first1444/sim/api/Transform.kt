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
        fun fromRadians(position: Vector2, rotationRadians: Double): Transform {
            return Transform(position, rotationRadians, toDegrees(rotationRadians))
        }
        fun fromDegrees(position: Vector2, rotationDegrees: Double): Transform {
            return Transform(position, toRadians(rotationDegrees), rotationDegrees)
        }
    }

    val x: Double
        get() = position.x
    val y: Double
        get() = position.y

    fun rotateRadians(angle: Double): Transform {
        return fromRadians(position.rotateRadians(angle), rotationRadians + angle)
    }
    fun rotateDegrees(angle: Double): Transform {
        return fromDegrees(position.rotateDegrees(angle), rotationDegrees + angle)
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
