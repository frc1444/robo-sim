package com.first1444.sim.api.drivetrain.swerve

import com.first1444.sim.api.Vector2

class PositionSwerveModule(
        val swerveModule: SwerveModule,
        val position: Vector2
)

class AnyWheelSwerveDriveData(
        val positionSwerveModules: List<PositionSwerveModule>
) : SwerveDriveData {
    override val modules: List<SwerveModule> = positionSwerveModules.map { it.swerveModule }
}
