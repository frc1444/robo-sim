package com.first1444.sim.api.frc.sim

import com.first1444.dashboard.BasicDashboard
import com.first1444.sim.api.frc.*

class DashboardFrcDriverStation(
        private val dashboard: BasicDashboard
) : FrcDriverStation {
    private fun getControlWord(): ControlWord {
        return ControlWord.fromWord(dashboard["FMSControlData"].getter.getDouble(0.0).toInt())
    }
    override val alliance: Alliance?
        get() = if(dashboard["IsRedAlliance"].getter.getBoolean(true)) Alliance.RED else Alliance.BLUE
    override val gameSpecificMessage: String
        get() = dashboard["GameSpecificMessage"].getter.getString("")
    override val isDriverStationAttached: Boolean
        get() = getControlWord().isDriverStationAttached
    override val isFMSAttached: Boolean
        get() = getControlWord().isFMSAttached
    override val driverStationLocationValue: Int
        get() = dashboard["StationNumber"].getter.getDouble(1.0).toInt()
    override val mode: FrcMode
        get() = getControlWord().mode
    override val matchInfo: MatchInfo
        get() = MatchInfo(
                dashboard["EventName"].getter.getString(""),
                MatchType.fromNumber(dashboard["MatchType"].getter.getDouble(-1.0).toInt()),
                dashboard["MatchNumber"].getter.getDouble(0.0).toInt(),
                dashboard["ReplayNumber"].getter.getDouble(0.0).toInt()
        )
    override val matchTime: Double?
        get() = null

}
