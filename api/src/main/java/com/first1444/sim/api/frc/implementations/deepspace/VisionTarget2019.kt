package com.first1444.sim.api.frc.implementations.deepspace

import com.first1444.sim.api.Transform2
import com.first1444.sim.api.frc.implementations.VisionTarget

class VisionTarget2019(
        override val transform: Transform2,
        override val identifier: VisionIdentifier2019
) : VisionTarget<VisionIdentifier2019> {
    override fun toString(): String {
        return "VisionTarget(transform=$transform, identifier=$identifier)"
    }
}

