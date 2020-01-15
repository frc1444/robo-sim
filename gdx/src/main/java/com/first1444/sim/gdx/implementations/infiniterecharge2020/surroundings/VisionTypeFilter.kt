package com.first1444.sim.gdx.implementations.infiniterecharge2020.surroundings

import com.first1444.sim.api.frc.implementations.infiniterecharge.VisionTarget2020
import com.first1444.sim.api.frc.implementations.infiniterecharge.VisionType2020
import com.first1444.sim.gdx.implementations.surroundings.VisionFilter

class VisionTypeFilter(
        private val visionType: VisionType2020
) : VisionFilter<VisionTarget2020> {
    override fun shouldInclude(visionTarget: VisionTarget2020): Boolean {
        return visionTarget.identifier.visionType == visionType
    }
}
