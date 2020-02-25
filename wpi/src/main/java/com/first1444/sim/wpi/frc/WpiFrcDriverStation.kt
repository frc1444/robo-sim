package com.first1444.sim.wpi.frc

import com.first1444.sim.api.frc.*
import edu.wpi.first.hal.HAL
import edu.wpi.first.wpilibj.DriverStation

class WpiFrcDriverStation(
        private val station: DriverStation
) : FrcDriverStation {
    override val alliance: Alliance?
        get() = when(station.alliance){
            DriverStation.Alliance.Blue -> Alliance.BLUE
            DriverStation.Alliance.Red -> Alliance.RED
            DriverStation.Alliance.Invalid -> null
            null -> throw NullPointerException("DriverStation.getAlliance() should not return null!")
        }
    override val matchInfo: MatchInfo
        get() = MatchInfo(
                station.eventName,
                when(station.matchType!!){
                    DriverStation.MatchType.Elimination -> MatchType.ELIMINATION
                    DriverStation.MatchType.Qualification -> MatchType.QUALIFICATION
                    DriverStation.MatchType.Practice -> MatchType.PRACTICE
                    DriverStation.MatchType.None -> MatchType.NONE
                },
                station.matchNumber,
                station.replayNumber
        )
    override val gameSpecificMessage: String
        get() = station.gameSpecificMessage

    override val controlWord: ControlWord
        get() = ControlWord(station.isEnabled, station.isAutonomous, station.isTest, station.isEStopped, station.isFMSAttached, station.isDSAttached)
    override val driverStationLocationValue: Int
        get() = station.location
    override val matchTime: Double?
        get() {
            val r = station.matchTime
            if(r == -1.0){
                return null
            }
            return r
        }

}
