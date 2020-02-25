package com.first1444.sim.api.frc.sim

import com.first1444.sim.api.frc.*

class FmsFrcDriverStation(
        private val fms: Fms,
        override val alliance: Alliance?,
        override val driverStationLocation: DriverStationLocation,
        private val gameSpecificMessageGetter: () -> String
): FrcDriverStation {
    override val driverStationLocationValue: Int = driverStationLocation.locationValue

    override val mode: FrcMode
        get() = fms.mode
    override val matchInfo: MatchInfo
        get() = fms.matchInfo
    override val controlWord: ControlWord
        get() = fms.controlWord
    override val matchTime: Double?
        get() = fms.matchTime

    override val gameSpecificMessage: String
        get() = gameSpecificMessageGetter()
}
