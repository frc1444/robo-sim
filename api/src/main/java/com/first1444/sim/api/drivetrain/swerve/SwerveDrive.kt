package com.first1444.sim.api.drivetrain.swerve

import com.first1444.sim.api.drivetrain.StrafeDrive

interface SwerveDrive : StrafeDrive {
    override val drivetrainData: SwerveDriveData
}
