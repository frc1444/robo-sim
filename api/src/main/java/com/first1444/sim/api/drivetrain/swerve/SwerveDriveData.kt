package com.first1444.sim.api.drivetrain.swerve

import com.first1444.sim.api.drivetrain.DrivetrainData

interface SwerveDriveData : DrivetrainData {
    val modules: List<SwerveModule>
}
