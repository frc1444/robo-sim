package com.first1444.sim.gdx.implementations.surroundings

import com.first1444.sim.api.frc.implementations.VisionTarget
import com.first1444.sim.gdx.entity.Entity

class EntityRangeVisionFilter(
        private val entity: Entity,
        range: Double
) : VisionFilter<VisionTarget<*>> {
    private val range2 = range * range
    override fun shouldInclude(visionTarget: VisionTarget<*>): Boolean {
        return entity.simVector.distance2(visionTarget.transform.position) <= range2
    }
}
