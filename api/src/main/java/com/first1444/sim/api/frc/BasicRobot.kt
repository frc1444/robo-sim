package com.first1444.sim.api.frc

interface BasicRobot : AutoCloseable {
    /**
     * @param mode The current [FrcMode]
     * @param previousMode The last [FrcMode]. May be the same as [mode]
     */
    fun update(mode: FrcMode, previousMode: FrcMode?)
}
