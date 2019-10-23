package com.first1444.sim.api.frc

abstract class BasicRobotRunnable(
        private val modeGetter: () -> FrcMode
) : Runnable {

    private var lastMode: FrcMode? = null

    constructor(driverStation: FrcDriverStation) : this(driverStation::mode)

    /**
     * @param mode The current [FrcMode]
     * @param previousMode The last [FrcMode]. May be the same as [mode]
     */
    protected abstract fun update(mode: FrcMode, previousMode: FrcMode?)

    override fun run() {
        val lastMode = this.lastMode
        val currentMode = modeGetter()
        if(currentMode != lastMode){
            this.lastMode = currentMode
        }
        update(currentMode, lastMode)
    }
}
