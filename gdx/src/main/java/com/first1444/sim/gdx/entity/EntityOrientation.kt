package com.first1444.sim.gdx.entity

import com.first1444.sim.api.Rotation2
import com.first1444.sim.api.sensors.Orientation

class EntityOrientation(
        private val entity: Entity
) : Orientation {
    override val orientation: Rotation2
        get() = entity.rotation
    override val orientationDegrees: Double
        get() = entity.rotationDegrees.toDouble()
    override val orientationRadians: Double
        get() = entity.rotationRadians.toDouble()

}
