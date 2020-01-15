package com.first1444.sim.api.frc.implementations.deepspace

import com.first1444.sim.api.frc.implementations.VisionIdentifier

data class VisionIdentifier2019(
        val isEnemy: Boolean,
        /**
         * True if left relative to the alliance it belongs to. False if right relative to the alliance it belongs to.
         */
        val isLeft: Boolean,
        val location: TargetLocation2019
) : VisionIdentifier
