package com.first1444.sim.gdx

import com.badlogic.gdx.math.Vector2


// TODO think about whether or not an entity needs to be able to be removed

interface Entity : Updateable {
    val position: Vector2
    val rotationDegrees: Float
    val rotationRadians: Float
}
