package com.first1444.sim.api.frc.sim

import com.first1444.dashboard.ActiveComponent
import com.first1444.dashboard.BasicDashboard
import com.first1444.dashboard.advanced.Sendable
import com.first1444.sim.api.frc.Alliance
import com.first1444.sim.api.frc.ControlWord
import com.first1444.sim.api.frc.FrcDriverStation

class DriverStationSendable(
        private val driverStation: FrcDriverStation
) : Sendable<ActiveComponent> {
    override fun init(title: String, dashboard: BasicDashboard): ActiveComponent {
        return object : ActiveComponent {
            override val title: String = title

            override fun onRemove() {
            }

            override fun update() {
                val matchInfo = driverStation.matchInfo
                dashboard["EventName"].strictSetter.setString(matchInfo.eventName)
                dashboard["FMSControlData"].strictSetter.setNumber(ControlWord(driverStation.mode, false, driverStation.isFMSAttached, driverStation.isDriverStationAttached).word)
                dashboard["GameSpecificMessage"].strictSetter.setString(driverStation.gameSpecificMessage)
                dashboard["IsRedAlliance"].strictSetter.setBoolean(driverStation.alliance == Alliance.RED)
                dashboard["MatchNumber"].strictSetter.setNumber(matchInfo.matchNumber)
                dashboard["MatchType"].strictSetter.setNumber(matchInfo.matchType.number)
                dashboard["ReplayNumber"].strictSetter.setNumber(matchInfo.replayNumber)
                dashboard["StationNumber"].strictSetter.setNumber(driverStation.driverStationLocationValue)
            }

        }
    }

}
