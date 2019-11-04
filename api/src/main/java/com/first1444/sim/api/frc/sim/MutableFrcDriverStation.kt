package com.first1444.sim.api.frc.sim

import com.first1444.sim.api.frc.*

/**
 * A [FrcDriverStation] implementation that is good for testing
 */
open class MutableFrcDriverStation : FrcDriverStation {
    override var alliance: Alliance? = Alliance.RED

    override var mode: FrcMode = FrcMode.DISABLED

    override var matchInfo: MatchInfo = MatchInfo("", null, 0, 0)
    override var gameSpecificMessage: String = ""

    override var isDriverStationAttached: Boolean = true
    override var isFMSAttached: Boolean = false
    override var driverStationLocationValue: Int = 1
        set(value) {
            check(value in 1..3)
            field = value
        }
    override var driverStationLocation: DriverStationLocation
        get() = super.driverStationLocation
        set(value) {
            driverStationLocationValue = value.locationValue
        }
    override var matchTime: Double? = null

}
