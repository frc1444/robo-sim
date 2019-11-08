package com.first1444.sim.api.surroundings

import com.first1444.sim.api.Transform

interface Surrounding {
    val transform: Transform
    val timestamp: Double
}