package com.first1444.sim.api.physics

import com.first1444.sim.api.Vector2

data class AcceleratorInstant(
        val position: Vector2,
        val velocity: Vector2
)

interface Accelerator {
    fun getAcceleratorInstant(): AcceleratorInstant
}
