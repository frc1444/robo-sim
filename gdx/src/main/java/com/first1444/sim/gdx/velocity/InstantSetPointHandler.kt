package com.first1444.sim.gdx.velocity

class InstantSetPointHandler : SetPointHandler {
    private var desired = 0.0f
    override val calculated: Float
        get() = desired

    override fun setDesired(desired: Float) {
        this.desired = desired
    }

    override fun forceSet(velocity: Float, reset: Boolean) {
        setDesired(velocity)
    }

    override fun update(delta: Float) {
    }

}
