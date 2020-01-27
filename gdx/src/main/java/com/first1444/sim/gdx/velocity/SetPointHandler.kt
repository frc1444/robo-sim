package com.first1444.sim.gdx.velocity

import com.first1444.sim.gdx.CloseableUpdateable

interface SetPointHandler : CloseableUpdateable {
    val calculated: Float

    fun setDesired(desired: Float)

    fun forceSet(velocity: Float, reset: Boolean = false)

}
