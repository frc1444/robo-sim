package com.first1444.sim.api.drivetrain.swerve

import com.first1444.sim.api.Vector2
import kotlin.math.*


class FourWheelSwerveDrive(
        override val drivetrainData: FourWheelSwerveDriveData
) : SwerveDrive {
    private var controlData: ControlData = ControlData.CONTROL_ZERO

    /** Wheel base:diagonal*/
    private val cosA: Double
    /** Track width:diagonal*/
    private val sinA: Double

    init {
        val diagonal = hypot(drivetrainData.wheelBase, drivetrainData.trackWidth)
        cosA = drivetrainData.wheelBase / diagonal
        sinA = drivetrainData.trackWidth / diagonal
    }

    override fun setControl(translation: Vector2, turnAmount: Double, speed: Double) {
        controlData = ControlData(translation, turnAmount, speed)
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

        val x = controlData.translation.x // FWD
        val y = controlData.translation.y // STR
        val turnAmount = controlData.turnAmount // RCW
        val speed = controlData.speed

        if (x == 0.0 && y == 0.0 && turnAmount == 0.0) {
            drivetrainData.apply {
                frontRight.setTargetSpeed(0.0)
                frontLeft.setTargetSpeed(0.0)
                rearLeft.setTargetSpeed(0.0)
                rearRight.setTargetSpeed(0.0)
            }
        } else {

            val sinA = this.sinA // wheel base // L/R
            val cosA = this.cosA // track width// W/R

            val A = y - turnAmount * cosA
            val B = y + turnAmount * cosA
            val C = x - turnAmount * sinA
            val D = x + turnAmount * sinA

            var frSpeed = hypot(A, C) * speed // ws1
            var flSpeed = hypot(A, D) * speed // ws2
            var rlSpeed = hypot(B, D) * speed // ws3
            var rrSpeed = hypot(B, C) * speed // ws4

            val max = max(max(frSpeed.absoluteValue, flSpeed.absoluteValue), max(rlSpeed.absoluteValue, rrSpeed.absoluteValue))
            if (max > 1) {
                frSpeed /= max
                flSpeed /= max
                rlSpeed /= max
                rrSpeed /= max
            }

            val frAngle = atan2(A, C)
            val flAngle = atan2(A, D)
            val rlAngle = atan2(B, D)
            val rrAngle = atan2(B, C)

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
