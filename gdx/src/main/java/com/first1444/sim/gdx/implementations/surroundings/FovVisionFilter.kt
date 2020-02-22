package com.first1444.sim.gdx.implementations.surroundings

import com.first1444.sim.api.Rotation2
import com.first1444.sim.api.Vector2
import com.first1444.sim.api.frc.implementations.VisionTarget
import com.first1444.sim.api.mod
import com.first1444.sim.gdx.entity.Entity
import kotlin.math.abs

class FovVisionFilter(
        private val entity: Entity,
        private val viewOffset: Rotation2,
        fov: Rotation2
) : VisionFilter<VisionTarget<*>> {

    private val fovRadians = mod(fov.radians, Math.PI * 2)

    override fun shouldInclude(visionTarget: VisionTarget<*>): Boolean {
        val position: Vector2 = entity.simVector
        val rotation: Rotation2 = entity.rotation
        val visionAngle: Rotation2 = visionTarget.transform.position.minus(position).angle

        val desired = visionAngle.minus(rotation).minus(viewOffset)
        return abs(desired.radians) <= fovRadians / 2.0
    }
}
