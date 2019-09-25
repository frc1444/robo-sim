package com.first1444.sim.wpi.output

import com.first1444.sim.api.output.MotorSpeedOutput
import edu.wpi.first.wpilibj.SpeedController

class SpeedControllerOutput(
        private val speedController: SpeedController
) : MotorSpeedOutput {
    override fun stop() {
        speedController.stopMotor()
    }

    override fun disable() {
        speedController.disable()
    }

    override fun setSpeed(speed: Double) {
        speedController.set(speed)
    }

}
