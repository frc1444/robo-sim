package com.first1444.sim.gdx.implementations.infiniterecharge2020.surroundings

import com.first1444.sim.api.Clock
import com.first1444.sim.api.frc.implementations.infiniterecharge.Extra2020
import com.first1444.sim.api.frc.implementations.infiniterecharge.Field2020
import com.first1444.sim.api.frc.implementations.infiniterecharge.VisionTarget2020
import com.first1444.sim.gdx.entity.Entity
import com.first1444.sim.gdx.implementations.surroundings.SimpleVisionProvider
import com.first1444.sim.gdx.implementations.surroundings.VisionFilter

class VisionProvider2020(
        visionFilter: VisionFilter<VisionTarget2020>,
        entity: Entity,
        clock: Clock
) : SimpleVisionProvider<VisionTarget2020>(Field2020.ALL_VISION_TARGETS, visionFilter, entity, clock) {
    override fun getExtra(vision: VisionTarget2020): Any? {
        return Extra2020(vision.identifier.visionType)
    }

}
