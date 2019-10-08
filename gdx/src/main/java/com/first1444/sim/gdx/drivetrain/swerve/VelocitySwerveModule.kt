package com.first1444.sim.gdx.drivetrain.swerve

import com.first1444.sim.api.Vector2
import com.first1444.sim.api.drivetrain.swerve.SwerveModule
import com.first1444.sim.gdx.physics.VelocityComponent
import com.first1444.sim.gdx.physics.VelocityInstant
import java.lang.Math.toDegrees
import java.lang.Math.toRadians

class VelocitySwerveModule(
        override val name: String,
        private val position: Vector2,
        private val maxVelocity: Double
) : SwerveModule, VelocityComponent {

    private var speed = 0.0
    private var angleRadians = 0.0

    override fun getVelocityInstant(): VelocityInstant {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun run() {

    }

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
    override val totalDistanceTraveledInches: Double
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val totalDistanceTraveledMeters: Double
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

}
