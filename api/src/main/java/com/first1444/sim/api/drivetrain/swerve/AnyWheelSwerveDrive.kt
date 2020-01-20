package com.first1444.sim.api.drivetrain.swerve

import com.first1444.sim.api.Vector2
import kotlin.math.absoluteValue
import kotlin.math.atan2
import kotlin.math.hypot

class AnyWheelSwerveDrive(
        override val drivetrainData: AnyWheelSwerveDriveData
) : SwerveDrive {

    private var controlData: ControlData = ControlData.CONTROL_ZERO

    override fun setControl(translation: Vector2, turnAmount: Double, speed: Double) {
        controlData = ControlData(translation, turnAmount, speed)
    }

    override fun run() {
        val controlData = this.controlData
        this.controlData = controlData.copy(speed = 0.0)

        val x = controlData.translation.x // FWD
        val y = controlData.translation.y // STR
        val turnAmount = controlData.turnAmount // RCW
        val speed = controlData.speed

        if (x == 0.0 && y == 0.0 && turnAmount == 0.0) {
            for(module in drivetrainData.modules){
                module.setTargetSpeed(0.0)
            }
        } else {
            val map = HashMap<PositionSwerveModule, Double>(drivetrainData.positionSwerveModules.size)
            var max = 0.0
            for(positionModule in drivetrainData.positionSwerveModules){
                val normalPosition = positionModule.position.normalized
                val module = positionModule.swerveModule
                val wheelX = x + turnAmount * normalPosition.y
                val wheelY = y - turnAmount * normalPosition.x
                val wheelSpeed = hypot(wheelX, wheelY) * speed
                val wheelAngle = atan2(wheelY, wheelX)
                map[positionModule] = wheelSpeed
                module.setTargetAngleRadians(wheelAngle)
                if(wheelSpeed.absoluteValue > max){
                    max = wheelSpeed.absoluteValue
                }
            }
            if(max > 1){
                for((positionModule, targetSpeed) in map){
                    positionModule.swerveModule.setTargetSpeed(targetSpeed / max)
                }
            } else {
                for((positionModule, targetSpeed) in map){
                    positionModule.swerveModule.setTargetSpeed(targetSpeed)
                }
            }
        }
    }

}
