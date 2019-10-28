package com.first1444.sim.api.frc

import com.first1444.sim.api.EnabledState
import com.first1444.sim.api.frc.sim.Fms

interface FrcDriverStation : Fms, EnabledState {
    val alliance: Alliance?
    /**
     * Also available using [matchInfo]
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
