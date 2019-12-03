package com.first1444.sim.api.distance

import com.first1444.sim.api.Vector2

class DefaultMutableDistanceAccumulator(
        private val distanceAccumulator: DistanceAccumulator,
        private val updateDistanceAccumulator: Boolean
) : MutableDistanceAccumulator {
    private var offset = Vector2.ZERO

    override var position: Vector2
        get() = distanceAccumulator.position - offset
        set(value) {
            offset = distanceAccumulator.position - value
        }

    override fun run() {
        if(updateDistanceAccumulator) {
            distanceAccumulator.run()
        }
    }

}
