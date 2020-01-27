package com.first1444.sim.gdx

import com.first1444.sim.api.Clock

class UpdateableClock : Clock, CloseableUpdateable {

    private var time: Double = 0.0

    override val timeSeconds: Double
        get() = time

    override fun update(delta: Float) {
        time += delta
    }
    override fun close() {
    }
}
