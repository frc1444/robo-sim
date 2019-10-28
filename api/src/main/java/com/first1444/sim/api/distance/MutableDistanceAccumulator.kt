package com.first1444.sim.api.distance

import com.first1444.sim.api.Vector2

interface MutableDistanceAccumulator : DistanceAccumulator {
    override var position: Vector2
}
