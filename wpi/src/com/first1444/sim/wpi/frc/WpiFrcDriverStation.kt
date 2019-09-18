package com.first1444.sim.wpi.frc

import com.first1444.sim.api.frc.*
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
    override val mode: FrcMode
        get() = when {
            station.isDisabled -> FrcMode.DISABLED
            station.isAutonomous -> FrcMode.AUTONOMOUS
            station.isOperatorControl -> FrcMode.TELEOP
            station.isTest -> FrcMode.TEST
            else -> throw IllegalStateException("The driver station is not in any mode?")
        }
    override val matchInfo: MatchInfo
        get() = MatchInfo(
                station.gameSpecificMessage,
                station.eventName,
                when(station.matchType!!){
                    DriverStation.MatchType.Elimination -> MatchType.ELIMINATION
                    DriverStation.MatchType.Practice -> MatchType.PRACTICE
                    DriverStation.MatchType.Qualification -> MatchType.QUALIFICATION
                    DriverStation.MatchType.None -> null
                },
                station.matchNumber,
                station.replayNumber
        )
    override val isDriverStationAttached: Boolean
        get() = station.isDSAttached
    override val isFMSAttached: Boolean
        get() = station.isFMSAttached
    override val driverStationLocationValue: Int
        get() = station.location
    override val matchTime: Double
        get() = station.matchTime

}
