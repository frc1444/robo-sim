package com.first1444.sim.gdx.implementations.surroundings

import com.first1444.sim.api.frc.implementations.VisionTarget

interface VisionFilter<in T : VisionTarget<*>> {
    fun shouldInclude(visionTarget: T): Boolean
}
