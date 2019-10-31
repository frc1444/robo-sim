package com.first1444.sim.gdx.entity

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body

interface BodyEntity : Entity {
    val body: Body

    override var position: Vector2
        get() = body.position
        set(value) {
            body.setTransform(value, body.angle)
        }

    override var rotationRadians: Float
        get() = body.angle
        set(value) {
            body.setTransform(body.position, value)
        }
    override var rotationDegrees: Float
        get() = rotationRadians * MathUtils.radiansToDegrees
        set(value) {
            rotationRadians = value * MathUtils.degreesToRadians
        }

    override fun setPosition(vector2: com.first1444.sim.api.Vector2) {
        body.setTransform(vector2.x.toFloat(), vector2.y.toFloat(), body.angle)
    }

    override fun setTransformRadians(position: com.first1444.sim.api.Vector2, rotationRadians: Float) =
            setTransformRadians(position.x.toFloat(), position.y.toFloat(), rotationRadians)
    override fun setTransformRadians(position: Vector2, rotationRadians: Float) =
            setTransformRadians(position.x, position.y, rotationRadians)
    override fun setTransformRadians(x: Float, y: Float, rotationRadians: Float) {
        body.setTransform(x, y, rotationRadians)
    }
    override fun setTransformDegrees(position: com.first1444.sim.api.Vector2, rotationDegrees: Float) =
            setTransformRadians(position, rotationDegrees * MathUtils.degreesToRadians)
    override fun setTransformDegrees(position: Vector2, rotationDegrees: Float) =
            setTransformRadians(position, rotationDegrees * MathUtils.degreesToRadians)
    override fun setTransformDegrees(x: Float, y: Float, rotationDegrees: Float) =
            setTransformRadians(x, y, rotationDegrees * MathUtils.degreesToRadians)
}
