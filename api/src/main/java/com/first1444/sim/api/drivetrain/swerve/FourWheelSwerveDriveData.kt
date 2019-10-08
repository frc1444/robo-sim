package com.first1444.sim.api.drivetrain.swerve

class FourWheelSwerveDriveData(
        val frontRight: SwerveModule,
        val frontLeft: SwerveModule,
        val rearLeft: SwerveModule,
        val rearRight: SwerveModule,
        /**
         * The distance from the rear wheels to the front wheels
         */
        val wheelBase: Double,
        /**
         * The distance from the left wheels to the right wheels
         */
        val trackWidth: Double
) : SwerveDriveData {
    override val modules: List<SwerveModule> = listOf(frontRight, frontLeft, rearLeft, rearRight)
}
