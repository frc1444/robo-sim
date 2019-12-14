package com.first1444.sim.api

object SystemNanosClock : Clock {
    override val timeSeconds: Double
        get() = System.nanoTime() / 1e9
}
