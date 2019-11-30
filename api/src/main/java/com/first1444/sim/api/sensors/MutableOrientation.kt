package com.first1444.sim.api.sensors

import com.first1444.sim.api.Rotation2

interface MutableOrientation : Orientation {
    override var orientation: Rotation2
    override var orientationDegrees: Double
    override var orientationRadians: Double
}
