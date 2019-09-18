package com.first1444.sim.wpi

import edu.wpi.first.wpilibj.DriverStation
import edu.wpi.first.wpilibj.RobotController

class TestClass {
    fun test(){
        val test = DriverStation.getInstance().eventName
        val test2 = RobotController.getFPGATime()
    }
}
