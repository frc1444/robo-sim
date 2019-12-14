package com.first1444.sim.api

object SystemMillisClock : Clock {
    override val timeSeconds: Double
        get() = System.currentTimeMillis() / 1000.0
}
