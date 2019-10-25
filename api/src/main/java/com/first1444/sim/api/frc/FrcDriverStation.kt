package com.first1444.sim.api.frc

import com.first1444.sim.api.EnabledState

interface FrcDriverStation : EnabledState {
    val alliance: Alliance?
    val mode: FrcMode
    val matchInfo: MatchInfo
    /**
     * Also available using [matchInfo]
     */
    val gameSpecificMessage: String

    val isDriverStationAttached: Boolean
    val isFMSAttached: Boolean

    val driverStationLocationValue: Int
    val driverStationLocation: DriverStationLocation
        get() = DriverStationLocation.getDriverStationLocation(driverStationLocationValue)

    /**
     * The amount of time left in the current [FrcMode] in seconds
     */
    val matchTime: Double

    override val isEnabled: Boolean
        get() = mode != FrcMode.DISABLED
}
