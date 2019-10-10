package com.first1444.sim.api.drivetrain.swerve

import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.hypot
import kotlin.math.max

private data class ControlData(
        val forward: Double,
        val strafe: Double,
        val turnAmount: Double,
        val speed: Double
) {
    init  {
        require(abs(forward) <= 1) { "forward must be in range [-1..1]" }
        require(abs(strafe) <= 1) { "strafe must be in range [-1..1]" }
        require(abs(turnAmount) <= 1) { "turnAmount must be in range [-1..1]" }
        require(abs(speed) <= 1) { "speed must be in range [-1..1]" }
    }
}

class FourWheelSwerveDrive(
        override val drivetrainData: FourWheelSwerveDriveData
) : SwerveDrive {
    companion object {
        private val CONTROL_ZERO: ControlData = ControlData(0.0, 0.0, 0.0, 0.0)
    }


    private var controlData: ControlData = CONTROL_ZERO

    /** Wheel base:diagonal*/
    private val cosA: Double
    /** Track width:diagonal*/
    private val sinA: Double

    init {
        val diagonal = hypot(drivetrainData.wheelBase, drivetrainData.trackWidth)
        cosA = drivetrainData.wheelBase / diagonal
        sinA = drivetrainData.trackWidth / diagonal
    }

    override fun setControl(forward: Double, strafe: Double, turnAmount: Double, speed: Double) {
        controlData = ControlData(forward, strafe, turnAmount, speed)
    }
    override fun run() {
        /*
        In this algorithm, 0 degrees is in the direction like so:
          ^
          |
        FL FR
        RL RR
        or more accurately:
        RL FL
             ======>
        RR FR
         */

        val controlData = this.controlData
        this.controlData = controlData.copy(speed = 0.0)

        val forward = controlData.forward // FWD
        val strafe = controlData.strafe // STR
        val turnAmount = controlData.turnAmount // RCW
        val speed = controlData.speed

        if (forward == 0.0 && strafe == 0.0 && turnAmount == 0.0) {
            drivetrainData.apply {
                frontRight.setTargetSpeed(0.0)
                frontLeft.setTargetSpeed(0.0)
                rearLeft.setTargetSpeed(0.0)
                rearRight.setTargetSpeed(0.0)
            }
        } else {

            val sinA = this.sinA // wheel base // L/R
            val cosA = this.cosA // track width// W/R

            val A = strafe - turnAmount * sinA
            val B = strafe + turnAmount * sinA
            val C = forward - turnAmount * cosA
            val D = forward + turnAmount * cosA

            var frSpeed = hypot(B, C) * speed // ws1
            var flSpeed = hypot(B, D) * speed // ws2
            var rlSpeed = hypot(A, D) * speed // ws3
            var rrSpeed = hypot(A, C) * speed // ws4

            val max = max(max(frSpeed, flSpeed), max(rlSpeed, rrSpeed))
            if (max > 1) {
                frSpeed /= max
                flSpeed /= max
                rlSpeed /= max
                rrSpeed /= max
            }

            // These are all negative because we want these to be regular counter clock wise angles
            // The tutorial/diagram that was followed had angles that were clock wise angles, which we don't want
            val frAngle = -atan2(B, C) // wa1
            val flAngle = -atan2(B, D) // wa2
            val rlAngle = -atan2(A, D) // wa3
            val rrAngle = -atan2(A, C) // wa4

            drivetrainData.apply {
                frontRight.setTargetAngleRadians(frAngle)
                frontRight.setTargetSpeed(frSpeed)
                frontLeft.setTargetAngleRadians(flAngle)
                frontLeft.setTargetSpeed(flSpeed)
                rearLeft.setTargetAngleRadians(rlAngle)
                rearLeft.setTargetSpeed(rlSpeed)
                rearRight.setTargetAngleRadians(rrAngle)
                rearRight.setTargetSpeed(rrSpeed)
            }
        }
        drivetrainData.apply {
            frontRight.run()
            frontLeft.run()
            rearLeft.run()
            rearRight.run()
        }


    }
}
