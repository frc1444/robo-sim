package com.first1444.sim.api.frc

import com.first1444.sim.api.RobotRunnable

open class BasicRobotRunnable(
        private val basicRobot: BasicRobot,
        private val modeGetter: () -> FrcMode
) : RobotRunnable {

    private var lastMode: FrcMode? = null

    constructor(basicRobot: BasicRobot, driverStation: FrcDriverStation) : this(basicRobot, driverStation::mode)

    override fun run() {
        val lastMode = this.lastMode
        val currentMode = modeGetter()
        if(currentMode != lastMode){
            this.lastMode = currentMode
        }
        basicRobot.update(currentMode, lastMode)
    }
    @Throws(Exception::class)
    override fun close() {
        basicRobot.close()
    }
}
