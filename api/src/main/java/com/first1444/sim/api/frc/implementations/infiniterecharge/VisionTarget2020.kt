package com.first1444.sim.api.frc.implementations.infiniterecharge

import com.first1444.sim.api.Transform2
import com.first1444.sim.api.frc.implementations.VisionTarget

class VisionTarget2020(
        override val transform: Transform2,
        override val identifier: VisionIdentifier2020
) : VisionTarget<VisionIdentifier2020> {
    override fun toString(): String {
        return "VisionTarget2020(transform=$transform, identifier=$identifier)"
    }
}
