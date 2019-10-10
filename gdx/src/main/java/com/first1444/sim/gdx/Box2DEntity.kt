package com.first1444.sim.gdx

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body

interface Box2DEntity : Entity {
    val body: Body

    override var position: Vector2
        get() = body.position
        set(value) {
            body.setTransform(value, body.angle)
        }

    override val rotationRadians: Float
        get() = body.angle
    override val rotationDegrees: Float
        get() = rotationRadians * MathUtils.radiansToDegrees
}
