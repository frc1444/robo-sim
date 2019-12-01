package com.first1444.sim.gdx.implementations.deepspace2019.surroundings

import com.first1444.sim.api.Clock
import com.first1444.sim.api.Rotation2
import com.first1444.sim.api.Transform2.Companion.fromRadians
import com.first1444.sim.api.frc.implementations.deepspace.Field2019
import com.first1444.sim.api.surroundings.Surrounding
import com.first1444.sim.api.surroundings.Surrounding3DExtra
import com.first1444.sim.api.surroundings.SurroundingProvider
import com.first1444.sim.gdx.entity.Entity

class VisionProvider(
        private val entity: Entity,
        range: Double,
        private val clock: Clock
): SurroundingProvider {
    private val range2 = range * range

    override val surroundings: List<Surrounding>
        get() {
            val r = ArrayList<Surrounding>()
            val transform = entity.simTransform
            for(vision in Field2019.ALL_VISION_TARGETS){
                val visionTransform = vision.transform
                if(transform.position.distance2(visionTransform.position) > range2) continue

                val offset = visionTransform.position - transform.position
                val angle = visionTransform.rotationRadians
                r.add(Surrounding(
                        fromRadians(offset, angle).rotateRadians(-transform.rotationRadians),
                        clock.timeSeconds,
                        Surrounding3DExtra(vision.identifier.location.bayType.visionType.centerHeight, Rotation2.ZERO, Rotation2.ZERO)
                ))
            }
            return r
        }

}
