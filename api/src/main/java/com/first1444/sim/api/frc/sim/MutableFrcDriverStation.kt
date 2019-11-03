package com.first1444.sim.api.frc.sim

import com.first1444.sim.api.frc.Alliance
import com.first1444.sim.api.frc.FrcDriverStation
import com.first1444.sim.api.frc.FrcMode
import com.first1444.sim.api.frc.MatchInfo

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
    override var matchTime: Double? = null

}
