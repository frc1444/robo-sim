package com.first1444.sim.gdx.entity

import com.badlogic.gdx.math.Vector2
import com.first1444.sim.gdx.Updateable
import com.first1444.sim.gdx.gdx


// TODO think about whether or not an entity needs to be able to be removed

interface Entity : Updateable {
    var position: Vector2
    var rotationDegrees: Float
    var rotationRadians: Float

    fun setTransformRadians(position: Vector2, rotationRadians: Float)
    fun setTransformRadians(position: com.first1444.sim.api.Vector2, rotationRadians: Float)
    fun setTransformRadians(x: Float, y: Float, rotationRadians: Float)

    fun setTransformDegrees(position: com.first1444.sim.api.Vector2, rotationDegrees: Float)
    fun setTransformDegrees(position: Vector2, rotationDegrees: Float)
    fun setTransformDegrees(x: Float, y: Float, rotationDegrees: Float)

    fun setPosition(vector2: com.first1444.sim.api.Vector2){
        position = vector2.gdx
    }
}
