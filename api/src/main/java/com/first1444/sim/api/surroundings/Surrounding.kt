package com.first1444.sim.api.surroundings

import com.first1444.sim.api.Transform

class Surrounding (
    val transform: Transform,
    val timestamp: Double,
    val extraData: Any?
) {
    override fun toString(): String {
        return "Surrounding(transform=$transform, timestamp=$timestamp, extraData=$extraData)"
    }
}