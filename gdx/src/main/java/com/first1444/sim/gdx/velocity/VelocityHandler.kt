package com.first1444.sim.gdx.velocity

import com.first1444.sim.gdx.Updateable

interface VelocityHandler : Updateable {
    val calculatedVelocity: Float

    fun setDesiredVelocity(desired: Float)

    fun forceSetVelocity(velocity: Float, reset: Boolean = false)

}
