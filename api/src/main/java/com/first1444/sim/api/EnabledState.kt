package com.first1444.sim.api

interface EnabledState {
    /**
     * @return true if motors and other components on the robot are or can be active, false otherwise
     */
    val isEnabled: Boolean
}
