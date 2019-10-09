package com.first1444.sim.gdx

import com.badlogic.gdx.math.Vector2
import com.first1444.sim.gdx.physics.VelocityData

class EntityVelocityApplier(
        private val entity: Box2DEntity,
        private val velocityData: VelocityData
) : Updateable{
    private val temp1 = Vector2()
    private val temp2 = Vector2()

    override fun update(delta: Float) {
        val body = entity.body
        for(component in velocityData.velocityComponents){
            val entityRotation = entity.rotationRadians.toDouble()
            val entityPosition = entity.position

            val instant = component.velocityInstant
            val velocity = instant.velocity.rotateRadians(entityRotation) // rotate the velocity to be applied in the correct direction

            val worldPosition = instant.position.rotateRadians(entityRotation) + entityPosition.sim
            body.applyForce(velocity.copyTo(temp1), worldPosition.copyTo(temp2), false)
        }
    }
}
