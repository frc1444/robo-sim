package com.first1444.sim.api.frc.implementations.deepspace

import com.first1444.sim.api.Transform2

class VisionTarget(
        val transform: Transform2,
        val identifier: VisionIdentifier
) {
    override fun toString(): String {
        return "VisionTarget(transform=$transform, identifier=$identifier)"
    }
}

