package com.first1444.sim.api.sensors

import com.first1444.sim.api.Rotation2
import java.lang.Math.toDegrees
import java.lang.Math.toRadians

class DefaultMutableOrientation(
        orientation: Orientation
) : MutableOrientation {
    private val myOrientation = orientation
    private var offsetDegrees: Double = 0.0

    override var orientationDegrees: Double
        get() = myOrientation.orientationDegrees - offsetDegrees
        set(value) {
            offsetDegrees = myOrientation.orientationDegrees - value
        }
    override var orientationRadians: Double
        get() = toRadians(orientationDegrees)
        set(value) {
            orientationDegrees = toDegrees(value)
        }
    override var orientation: Rotation2
        get() = Rotation2.fromDegrees(orientationDegrees)
        set(value) { orientationDegrees = value.degrees }

}
