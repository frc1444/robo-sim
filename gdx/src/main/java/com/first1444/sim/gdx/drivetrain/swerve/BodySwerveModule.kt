package com.first1444.sim.gdx.drivetrain.swerve

import com.badlogic.gdx.physics.box2d.Body
import com.first1444.sim.api.Clock
import com.first1444.sim.api.EnabledState
import com.first1444.sim.api.Rotation2
import com.first1444.sim.api.drivetrain.swerve.SwerveModule
import com.first1444.sim.api.event.EventHandler
import com.first1444.sim.api.minChange
import com.first1444.sim.gdx.gdxVectorFromRadians
import com.first1444.sim.gdx.velocity.SetPointHandler

class BodySwerveModule(
        override val name: String,
        private val body: Body,
        private val parentBody: Body,
        /**
         * The maximum velocity in meters/second
         */
        private val maxVelocity: Double,
        private val clock: Clock,
        private val enabledState: EnabledState,
        private val driveVelocitySetPointHandler: SetPointHandler,
        private val angleRadiansSetPointHandler: SetPointHandler
) : SwerveModule {
    private var speed = 0.0
    private var angle = 0.0

    private var lastTimestamp: Double? = null
    override var distanceTraveledMeters: Double = 0.0
        private set

    override fun run() {

        val timestamp = clock.timeSeconds
        val lastTimestamp = this.lastTimestamp
        if(lastTimestamp != null){
            val delta = timestamp - lastTimestamp

            val isEnabled = enabledState.isEnabled

            // angle stuff
            if(isEnabled) {
                val current = currentAngleRadians
                val change = minChange(angle, current, Math.PI * 2)
                angleRadiansSetPointHandler.setDesired((current + change).toFloat())
            }
            angleRadiansSetPointHandler.update(delta.toFloat())
            val angleRadians = (currentAngleRadians + parentBody.angle).toFloat()
            body.setTransform(body.position, angleRadians)

            if(isEnabled) {
                // speed stuff
                val velocity = speed * maxVelocity
                driveVelocitySetPointHandler.setDesired(velocity.toFloat())
            } else {
                driveVelocitySetPointHandler.setDesired(0.0f)
            }
            driveVelocitySetPointHandler.update(delta.toFloat())
            val newVelocity = driveVelocitySetPointHandler.calculated
            body.linearVelocity = gdxVectorFromRadians(body.angle, newVelocity)

            distanceTraveledMeters += newVelocity * delta
        }
        this.lastTimestamp = timestamp
    }

    override val eventHandler: EventHandler = EventHandler.DO_NOTHING

    override fun setTargetSpeed(speed: Double) {
        this.speed = speed
    }

    override fun setTargetAngle(position: Rotation2) {
        setTargetAngleRadians(position.radians)
    }
    override fun setTargetAngleDegrees(positionDegrees: Double) {
        setTargetAngleRadians(Math.toRadians(positionDegrees))
    }

    override fun setTargetAngleRadians(positionRadians: Double) {
        angle = positionRadians
    }

    override val currentAngle: Rotation2
        get() = Rotation2.fromRadians(currentAngleRadians)
    override val currentAngleDegrees: Double
        get() = Math.toDegrees(currentAngleRadians)
    override val currentAngleRadians: Double
        get() = angleRadiansSetPointHandler.calculated.toDouble()

}

