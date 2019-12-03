package com.first1444.sim.api.distance

import com.first1444.sim.api.Vector2

class MutableDeltaDistanceAccumulator(
        private val deltaDistanceCalculator: DeltaDistanceCalculator
) : MutableDistanceAccumulator {
    override var position: Vector2 = Vector2.ZERO

    override fun run() {
        position += deltaDistanceCalculator.getDelta()
    }

}
