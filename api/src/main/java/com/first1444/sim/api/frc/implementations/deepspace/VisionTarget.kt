package com.first1444.sim.api.frc.implementations.deepspace

import com.first1444.sim.api.Transform

class VisionTarget(
        val transform: Transform,
        val identifier: VisionIdentifier
) {
    override fun toString(): String {
        return "VisionTarget(transform=$transform, identifier=$identifier)"
    }
}
