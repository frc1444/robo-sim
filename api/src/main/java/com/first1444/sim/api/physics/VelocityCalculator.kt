package com.first1444.sim.api.physics

import com.first1444.sim.api.Vector2

object VelocityCalculator {
    @JvmStatic
    fun calculateVelocity(acceleratorInstantList: List<AcceleratorInstant>): Velocity{
        var velocitySum = Vector2.ZERO
        var positionSum = Vector2.ZERO
        for(acceleratorInstant in acceleratorInstantList){
            velocitySum += acceleratorInstant.velocity
            positionSum += acceleratorInstant.position
        }
        val velocity = velocitySum / acceleratorInstantList.size.toDouble()
        val centerPosition = positionSum / acceleratorInstantList.size.toDouble()

        return Velocity(VelocityR(velocity, 0.0)) // TODO rotational velocity
    }
}
