package com.first1444.sim.api.distance


import com.first1444.sim.api.Vector2

class DeltaDistanceAccumulator(
        private val deltaDistanceCalculator: DeltaDistanceCalculator
) : DistanceAccumulator {
    override var position: Vector2 = Vector2.ZERO
        private set

    override fun run() {
        position += deltaDistanceCalculator.getDelta()
    }

}