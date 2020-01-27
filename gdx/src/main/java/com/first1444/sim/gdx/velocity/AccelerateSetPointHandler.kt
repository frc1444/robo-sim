package com.first1444.sim.gdx.velocity

class AccelerateSetPointHandler(
        private val positiveAccelerationPerSecond: Float,
        private val positiveDecelerationPerSecond: Float,
        private val negativeAccelerationPerSecond: Float,
        private val negativeDecelerationPerSecond: Float
) : SetPointHandler {
    init {
        require(positiveAccelerationPerSecond >= 0)
        require(positiveDecelerationPerSecond >= 0)
        require(negativeAccelerationPerSecond >= 0)
        require(negativeDecelerationPerSecond >= 0)
    }
    constructor(accelerationPerSecond: Float) : this(accelerationPerSecond, accelerationPerSecond, accelerationPerSecond, accelerationPerSecond)
    constructor(accelerationPerSecond: Float, decelerationPerSecond: Float) : this(accelerationPerSecond, decelerationPerSecond, accelerationPerSecond, decelerationPerSecond)

    private var velocity = 0.0f
    private var desiredVelocity = 0.0f
    override val calculated: Float
        get() = velocity

    override fun setDesired(desired: Float) {
        desiredVelocity = desired
    }

    override fun forceSet(velocity: Float, reset: Boolean) {
        this.velocity = velocity
        this.desiredVelocity = velocity
    }

    override fun update(delta: Float) {
        var current = this.velocity
        val desired = this.desiredVelocity

        if(current < 0){ // negative
            if(current < desired){ // we need to decelerate by adding to the current velocity
                val amount = negativeDecelerationPerSecond * delta
                current += amount
                if(current > desired){
                    current = desired
                }
            } else if(current > desired){ // we need to accelerate by subtracting from the current velocity
                val amount = negativeAccelerationPerSecond * delta
                current -= amount
                if(current < desired){
                    current = desired
                }
            }
        } else {
            if(current < desired){
                val amount = positiveAccelerationPerSecond * delta
                current += amount
                if(current > desired){
                    current = desired
                }
            } else if(current > desired){
                val amount = positiveDecelerationPerSecond * delta
                current -= amount
                if(current < desired){
                    current = desired
                }
            }

        }
        this.velocity = current
    }
    override fun close() {
    }

}
