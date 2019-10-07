package com.first1444.sim.gdx

import com.first1444.sim.gdx.physics.VelocityData

class EntityVelocityApplier(
        private val entity: Box2DEntity,
        private val velocityData: VelocityData
) : Updateable{
    override fun update(delta: Float) {
        val body = entity.body
        for(component in velocityData.velocityComponents){
            val instant = component.getVelocityInstant()
            val velocity = instant.velocity.gdx
            val worldPosition = (instant.position + body.position.sim).gdx
            body.applyForce(velocity, worldPosition, false)
        }
    }
}
