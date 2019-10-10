package com.first1444.sim.gdx.physics

import com.first1444.sim.gdx.BodyEntity
import com.first1444.sim.gdx.Updateable
import com.first1444.sim.gdx.set

class BodyVelocityApplier(
        private val entity: BodyEntity,
        private val parentEntity: BodyEntity,
        private val velocityComponent: VelocityComponent
) : Updateable {

    override fun update(delta: Float) {
        val instant = velocityComponent.velocityInstant
        val velocity = instant.velocity.rotateRadians(parentEntity.rotationRadians.toDouble()) // rotate the velocity to be applied in the correct direction

        val body = entity.body
        body.linearVelocity = body.linearVelocity.set(velocity)
    }
}

