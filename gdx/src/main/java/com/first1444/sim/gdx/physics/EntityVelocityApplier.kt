package com.first1444.sim.gdx.physics

import com.badlogic.gdx.math.Vector2
import com.first1444.sim.gdx.Box2DEntity
import com.first1444.sim.gdx.Updateable
import com.first1444.sim.gdx.copyTo
import com.first1444.sim.gdx.sim

class EntityVelocityApplier(
        private val entity: Box2DEntity,
        private val velocityComponents: List<VelocityComponent>
) : Updateable {
    private val temp1 = Vector2()
    private val temp2 = Vector2()

    override fun update(delta: Float) {
        val body = entity.body
        for(component in velocityComponents){
            val entityRotation = entity.rotationRadians.toDouble()
            val entityPosition = entity.position

            val instant = component.velocityInstant
            val velocity = instant.velocity.rotateRadians(entityRotation) // rotate the velocity to be applied in the correct direction

            val worldPosition = instant.position.rotateRadians(entityRotation) + entityPosition.sim
            velocity.copyTo(temp1)
            worldPosition.copyTo(temp2)
//            body.applyForce(velocity.copyTo(temp1), worldPosition.copyTo(temp2), true)
            body.applyForce(temp1, temp2, true)
//            body.applyLinearImpulse(temp1, temp2, true)
        }
    }
}
