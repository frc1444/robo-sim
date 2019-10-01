package com.first1444.sim.api.physics

import com.first1444.sim.api.Vector2
import java.lang.Math.toDegrees
import java.lang.Math.toRadians
import java.util.*

data class VelocityD(
        val velocity: Vector2,
        val rotationalVelocityDegreesPerSecond: Double
)
data class VelocityR(
        val velocity: Vector2,
        val rotationalVelocityRadiansPerSecond: Double
)
class Velocity private constructor(
        private val velocity: Vector2,
        private val rotationalVelocityDegreesPerSecond: Double,
        private val rotationalVelocityRadiansPerSecond: Double
) {
    constructor(velocityD: VelocityD) : this(velocityD.velocity, velocityD.rotationalVelocityDegreesPerSecond, toRadians(velocityD.rotationalVelocityDegreesPerSecond))
    constructor(velocityR: VelocityR) : this(velocityR.velocity, toDegrees(velocityR.rotationalVelocityRadiansPerSecond), velocityR.rotationalVelocityRadiansPerSecond)

    override fun hashCode(): Int {
        return Objects.hash(velocity, rotationalVelocityDegreesPerSecond, rotationalVelocityRadiansPerSecond)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Velocity) return false

        if (velocity != other.velocity) return false
        if (rotationalVelocityDegreesPerSecond != other.rotationalVelocityDegreesPerSecond) return false
        if (rotationalVelocityRadiansPerSecond != other.rotationalVelocityRadiansPerSecond) return false

        return true
    }
}
