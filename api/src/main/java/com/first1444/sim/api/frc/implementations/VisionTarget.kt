package com.first1444.sim.api.frc.implementations

import com.first1444.sim.api.Transform2

interface VisionTarget<T : VisionIdentifier> {
    val transform: Transform2
    val identifier: T
}
