package com.first1444.sim.api.drivetrain.swerve

import com.first1444.sim.api.event.EventHandler

interface SwerveModule : Runnable {
    /**
     * Updates the swerve module with the target speed and angle. Should be called to apply the speed and angle
     */
    override fun run()


    val eventHandler: EventHandler

    /**
     * @param speed A number in range [-1..1] representing the speed as a percentage
     */
    fun setTargetSpeed(speed: Double)

    fun setTargetAngleDegrees(positionDegrees: Double)
    fun setTargetAngleRadians(positionRadians: Double)

    val currentAngleDegrees: Double
    val currentAngleRadians: Double

    /**
     * @return The distance that the swerve wheel has traveled. This number may go up or down
     */
    val distanceTraveledMeters: Double

    val name: String
}