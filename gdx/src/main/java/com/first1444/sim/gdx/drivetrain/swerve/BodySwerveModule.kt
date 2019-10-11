package com.first1444.sim.gdx.drivetrain.swerve

import com.badlogic.gdx.physics.box2d.Body
import com.first1444.sim.api.Clock
import com.first1444.sim.api.drivetrain.swerve.SwerveModule
import com.first1444.sim.api.event.EventHandler
import com.first1444.sim.gdx.GdxUtil.gdxVectorFromRadians
import com.first1444.sim.gdx.velocity.VelocityHandler
import kotlin.math.absoluteValue

class BodySwerveModule(
        override val name: String,
        private val body: Body,
        private val parentBody: Body,
        /**
         * The maximum velocity in meters/second
         */
        private val maxVelocity: Double,
        private val clock: Clock,
        private val velocityHandler: VelocityHandler
) : SwerveModule {

    private var speed = 0.0
    private var angleRadians = 0.0


    private var lastTimestamp: Double? = null
    private var distanceTraveledMeters = 0.0

    override fun run() {

        val timestamp = clock.timeSeconds
        val lastTimestamp = this.lastTimestamp
        if(lastTimestamp != null){
            val delta = timestamp - lastTimestamp

            val speed = this.speed
            val maxVelocity = this.maxVelocity
            val velocity = (speed * maxVelocity).absoluteValue
            val angleRadians = this.angleRadians + parentBody.angle
            velocityHandler.setDesiredVelocity(velocity.toFloat())
            velocityHandler.update(delta.toFloat())
            val newVelocity = velocityHandler.calculatedVelocity
            body.linearVelocity = gdxVectorFromRadians(angleRadians.toFloat(), newVelocity)

            distanceTraveledMeters += velocity * delta
        }
        this.lastTimestamp = timestamp
    }

    override val eventHandler: EventHandler = EventHandler.DO_NOTHING

    override fun setTargetSpeed(speed: Double) {
        this.speed = speed
    }

    override fun setTargetAngleDegrees(positionDegrees: Double) {
        setTargetAngleRadians(Math.toRadians(positionDegrees))
    }

    override fun setTargetAngleRadians(positionRadians: Double) {
        this.angleRadians = positionRadians
    }

    override val currentAngleDegrees: Double
        get() = Math.toDegrees(currentAngleRadians)
    override val currentAngleRadians: Double
        get() = angleRadians
    override val totalDistanceTraveledMeters: Double
        get() = distanceTraveledMeters

}

