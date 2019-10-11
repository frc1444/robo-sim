package com.first1444.sim.gdx.physics

import com.first1444.sim.gdx.BodyEntity
import com.first1444.sim.gdx.Updateable
import com.first1444.sim.gdx.gdx
import com.first1444.sim.gdx.velocity.VelocityHandler

@Deprecated("Use BodySwerveModule")
class BodyVelocityApplier(
        private val entity: BodyEntity,
        private val parentEntity: BodyEntity,
        private val velocityComponent: VelocityComponent,
        private val velocityHandler: VelocityHandler
) : Updateable {

    init {
        velocityHandler.forceSetVelocity(0.0f, true)
    }

    override fun update(delta: Float) {
        val instant = velocityComponent.velocityInstant
        val velocity = instant.velocity.rotateRadians(parentEntity.rotationRadians.toDouble()).gdx // rotate the velocity to be applied in the correct direction
        val desiredMagnitude = velocity.len()
        velocityHandler.setDesiredVelocity(desiredMagnitude)
        velocityHandler.update(delta)
        val magnitude = velocityHandler.calculatedVelocity
        velocity.nor().scl(magnitude)

        val body = entity.body
        body.linearVelocity = body.linearVelocity.set(velocity)
    }
}

