package com.first1444.sim.gdx

import com.badlogic.gdx.physics.box2d.Body
import com.first1444.sim.api.Transform2
import com.first1444.sim.api.Vector2
import java.lang.Math.toRadians

var Body.simTransform: Transform2
    get() = transform.sim
    set(value){
        setTransform(value.x.toFloat(), value.y.toFloat(), value.rotationRadians.toFloat())
    }

var Body.gdxTransform: com.badlogic.gdx.physics.box2d.Transform
    get() = transform
    set(value) {
        val position = value.position
        setTransform(position.x, position.y, value.rotation)
    }

fun Body.setTransformRadians(x: Double, y: Double, angleRadians: Double) {
    setTransform(x.toFloat(), y.toFloat(), angleRadians.toFloat())
}
fun Body.setTransformRadians(position: Vector2, angleRadians: Double) {
    setTransformRadians(position.x, position.y, angleRadians)
}

fun Body.setTransformDegrees(x: Double, y: Double, angleDegrees: Double) {
    setTransform(x.toFloat(), y.toFloat(), toRadians(angleDegrees).toFloat())
}
fun Body.setTransformDegrees(position: Vector2, angleDegrees: Double) {
    setTransformDegrees(position.x, position.y, angleDegrees)
}
