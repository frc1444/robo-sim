package com.first1444.sim.api.drivetrain.swerve

interface SwerveModule : Runnable {
    /**
     * Updates the swerve module with the target speed and angle. Should be called to apply the speed and angle
     */
    override fun run()


    // TODO figure out a more elegant way to receive method calls such as recalibrate (an event system?)
    fun recalibrate() {}

    /**
     * @param speed A number in range [-1..1] representing the speed as a percentage
     */
    fun setTargetSpeed(speed: Double)

    fun setTargetAngleDegrees(positionDegrees: Double)
    fun setTargetAngleRadians(positionRadians: Double)

    val currentAngleDegrees: Double
    val currentAngleRadians: Double

    val totalDistanceTraveledMeters: Double

    val name: String
}