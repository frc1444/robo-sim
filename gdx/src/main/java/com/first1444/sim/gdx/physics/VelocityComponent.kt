package com.first1444.sim.gdx.physics

import com.first1444.sim.api.Vector2

data class VelocityInstant(
        val position: Vector2,
        val velocity: Vector2
)

interface VelocityComponent {
    val velocityInstant: VelocityInstant
}
