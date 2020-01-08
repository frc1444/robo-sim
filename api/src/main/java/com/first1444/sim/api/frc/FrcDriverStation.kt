package com.first1444.sim.api.frc

import com.first1444.sim.api.EnabledState

interface FrcDriverStation : Fms, EnabledState {
    val alliance: Alliance?
    /**
     * Also available using [matchInfo]
     *
     * NOTE: This message may change throughout the course of the match. In 2018, this was available upon autonomousInit(). In 2019, this is available
     * after charging stage 3.
     *
     * @return The game specific message
     */
    val gameSpecificMessage: String

    val isDriverStationAttached: Boolean
    val isFMSAttached: Boolean

    val driverStationLocationValue: Int
    val driverStationLocation: DriverStationLocation
        get() = DriverStationLocation.getDriverStationLocation(driverStationLocationValue)

    override val isEnabled: Boolean
        get() = mode != FrcMode.DISABLED
}
