package com.first1444.sim.gdx

import com.first1444.sim.api.sensors.Orientation

class EntityOrientation(
        private val entity: Entity
) : Orientation {
    override val orientationDegrees: Double
        get() = entity.rotationDegrees.toDouble()
    override val orientationRadians: Double
        get() = entity.rotationRadians.toDouble()

}
