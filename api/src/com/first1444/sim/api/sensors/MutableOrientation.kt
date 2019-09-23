package com.first1444.sim.api.sensors

interface MutableOrientation : Orientation {
    override var orientationDegrees: Double
    override var orientationRadians: Double
}
