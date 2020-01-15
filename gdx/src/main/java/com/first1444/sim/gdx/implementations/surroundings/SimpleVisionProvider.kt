package com.first1444.sim.gdx.implementations.surroundings

import com.first1444.sim.api.Clock
import com.first1444.sim.api.Transform2
import com.first1444.sim.api.frc.implementations.VisionTarget
import com.first1444.sim.api.surroundings.Surrounding
import com.first1444.sim.api.surroundings.SurroundingProvider
import com.first1444.sim.gdx.entity.Entity

abstract class SimpleVisionProvider<T : VisionTarget<*>>(
        private val targets: List<T>,
        private val visionFilter: VisionFilter<T>,
        private val entity: Entity,
        private val clock: Clock
) : SurroundingProvider {
    final override val surroundings: List<Surrounding>
        get() {
            val r = ArrayList<Surrounding>()
            val transform = entity.simTransform
            for(vision in targets){
                if(!visionFilter.shouldInclude(vision)) continue

                val visionTransform = vision.transform
                val offset = visionTransform.position - transform.position
                val angle = visionTransform.rotationRadians
                r.add(Surrounding(
                        Transform2.fromRadians(offset, angle).rotateRadians(-transform.rotationRadians),
                        clock.timeSeconds,
                        getExtra(vision)
                ))
            }
            return r
        }

    abstract fun getExtra(vision: T): Any?

}
