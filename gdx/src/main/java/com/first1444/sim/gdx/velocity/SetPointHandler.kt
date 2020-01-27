package com.first1444.sim.gdx.velocity

import com.first1444.sim.gdx.Updateable

interface SetPointHandler : Updateable {
    val calculated: Float

    fun setDesired(desired: Float)

    fun forceSet(velocity: Float, reset: Boolean = false)

}
