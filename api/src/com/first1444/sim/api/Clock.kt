package com.first1444.sim.api

interface Clock {
    /**
     * @return The time in seconds. This doesn't represent a particular spot in time, it is just guaranteed to count up
     */
    val timeSeconds: Double
}
