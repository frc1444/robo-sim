package com.first1444.sim.gdx.implementations.surroundings

import com.first1444.sim.api.frc.implementations.VisionTarget

class VisionFilterMultiplexer<in T : VisionTarget<*>>(
        private val visionFilters: List<VisionFilter<T>>
) : VisionFilter<T> {
    override fun shouldInclude(visionTarget: T): Boolean {
        for(visionFilter in visionFilters){
            if(!visionFilter.shouldInclude(visionTarget)){
                return false
            }
        }
        return true
    }

}
