package com.first1444.sim.api.sensors

import java.lang.Math.toDegrees
import java.lang.Math.toRadians

class DefaultMutableOrientation(
        private val orientation: Orientation
) : MutableOrientation {
    private var offsetDegrees: Double = 0.0

    override var orientationDegrees: Double
        get() = orientation.orientationDegrees - offsetDegrees
        set(value) {
            offsetDegrees = orientation.orientationDegrees - value
        }
    override var orientationRadians: Double
        get() = toRadians(orientationDegrees)
        set(value) {
            orientationDegrees = toDegrees(value)
        }

}
