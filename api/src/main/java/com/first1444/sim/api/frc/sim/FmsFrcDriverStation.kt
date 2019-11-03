package com.first1444.sim.api.frc.sim

import com.first1444.sim.api.frc.*

class FmsFrcDriverStation(
        private val fms: Fms,
        override val alliance: Alliance?,
        override val driverStationLocation: DriverStationLocation,
        override val gameSpecificMessage: String
): FrcDriverStation {
    override val driverStationLocationValue: Int = driverStationLocation.locationValue

    override val mode: FrcMode
        get() = fms.mode
    override val matchInfo: MatchInfo
        get() = fms.matchInfo
    override val isDriverStationAttached: Boolean
        get() = true
    override var isFMSAttached: Boolean = false

    override val matchTime: Double?
        get() = fms.matchTime

}
