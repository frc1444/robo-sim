package com.first1444.sim.wpi.sensors

import com.first1444.sim.api.sensors.DigitalInput

class WpiDigitalInput @JvmOverloads constructor(
        private val digitalInput: edu.wpi.first.wpilibj.DigitalInput,
        private val isInverted: Boolean = false
) : DigitalInput {

    constructor(
            digitalInput: edu.wpi.first.wpilibj.DigitalInput,
            isTrueClosed: Boolean,
            isNormallyClosed: Boolean
    ) : this(digitalInput, isTrueClosed == isNormallyClosed)

    override val value: Boolean
        get() = digitalInput.get() != isInverted

}
