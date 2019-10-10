package com.first1444.sim.gdx.drivetrain.swerve

import com.first1444.sim.api.Clock
import com.first1444.sim.api.Vector2
import com.first1444.sim.api.drivetrain.swerve.SwerveModule
import com.first1444.sim.api.event.EventHandler
import com.first1444.sim.gdx.physics.VelocityComponent
import com.first1444.sim.gdx.physics.VelocityInstant
import java.lang.Math.toDegrees
import java.lang.Math.toRadians
import kotlin.math.absoluteValue
import kotlin.math.cos
import kotlin.math.sin

class VelocitySwerveModule(
        override val name: String,
        val position: Vector2,
        /**
         * The maximum velocity in meters/second
         */
        private val maxVelocity: Double,
        private val clock: Clock
) : SwerveModule, VelocityComponent {

    private var speed = 0.0
    private var angleRadians = 0.0

    private var lastVelocityInstant: VelocityInstant? = null

    override val velocityInstant: VelocityInstant
        get() {
            return lastVelocityInstant ?: throw IllegalStateException("You have to call run() before you can access this!")
        }

    private var lastTimestamp: Double? = null
    private var distanceTraveledMeters = 0.0

    override fun run() {
        val speed = this.speed
        val angleRadians = this.angleRadians
        val maxVelocity = this.maxVelocity
        lastVelocityInstant = VelocityInstant(
                position,
                Vector2(
                        cos(angleRadians) * maxVelocity * speed,
                        sin(angleRadians) * maxVelocity * speed
                )
        )
        val velocity = (speed * maxVelocity).absoluteValue
        val timestamp = clock.timeSeconds
        val lastTimestamp = this.lastTimestamp
        if(lastTimestamp != null){
            val delta = timestamp - lastTimestamp
            distanceTraveledMeters += velocity * delta
        }
        this.lastTimestamp = timestamp
    }

    override val eventHandler: EventHandler = EventHandler.DO_NOTHING

    override fun setTargetSpeed(speed: Double) {
        this.speed = speed
    }

    override fun setTargetAngleDegrees(positionDegrees: Double) {
        setTargetAngleRadians(toRadians(positionDegrees))
    }

    override fun setTargetAngleRadians(positionRadians: Double) {
        this.angleRadians = positionRadians
    }

    override val currentAngleDegrees: Double
        get() = toDegrees(currentAngleRadians)
    override val currentAngleRadians: Double
        get() = angleRadians
    override val totalDistanceTraveledMeters: Double
        get() = distanceTraveledMeters

}
