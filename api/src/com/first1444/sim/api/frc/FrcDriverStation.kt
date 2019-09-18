package com.first1444.sim.api.frc

interface FrcDriverStation {
    val alliance: Alliance?
    val mode: FrcMode
    val matchInfo: MatchInfo
    val isDriverStationAttached: Boolean
    val isFMSAttached: Boolean

    val driverStationLocationValue: Int
    val driverStationLocation: DriverStationLocation
        get() = DriverStationLocation.getDriverStationLocation(driverStationLocationValue)

    /**
     * The amount of time left in the current [FrcMode] in seconds
     */
    val matchTime: Double
}
