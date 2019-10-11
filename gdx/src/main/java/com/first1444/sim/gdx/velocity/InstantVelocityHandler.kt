package com.first1444.sim.gdx.velocity

class InstantVelocityHandler : VelocityHandler {
    private var desired = 0.0f
    override val calculatedVelocity: Float
        get() = desired

    override fun setDesiredVelocity(desired: Float) {
        this.desired = desired
    }

    override fun forceSetVelocity(velocity: Float, reset: Boolean) {
        setDesiredVelocity(velocity)
    }

    override fun update(delta: Float) {
    }

}
