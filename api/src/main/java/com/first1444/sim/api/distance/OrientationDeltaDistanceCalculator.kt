package com.first1444.sim.api.distance

import com.first1444.sim.api.Vector2
import com.first1444.sim.api.sensors.Orientation

class OrientationDeltaDistanceCalculator(
        private val deltaDistanceCalculator: DeltaDistanceCalculator,
        private val orientation: Orientation
) : DeltaDistanceCalculator {
    override fun getDelta(): Vector2 {
        return deltaDistanceCalculator.getDelta().rotateRadians(orientation.orientationRadians)
    }

}
