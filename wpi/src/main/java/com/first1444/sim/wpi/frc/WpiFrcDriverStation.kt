package com.first1444.sim.wpi.frc

import com.first1444.sim.api.frc.*
import edu.wpi.first.wpilibj.DriverStation

object WpiFrcDriverStation : FrcDriverStation {
    override val alliance: Alliance?
        get() = when(DriverStation.getAlliance()){
            DriverStation.Alliance.Blue -> Alliance.BLUE
            DriverStation.Alliance.Red -> Alliance.RED
            DriverStation.Alliance.Invalid -> null
            null -> throw NullPointerException("DriverStation.getAlliance() should not return null!")
        }
    override val matchInfo: MatchInfo
        get() = MatchInfo(
                DriverStation.getEventName(),
                when(DriverStation.getMatchType()!!){
                    DriverStation.MatchType.Elimination -> MatchType.ELIMINATION
                    DriverStation.MatchType.Qualification -> MatchType.QUALIFICATION
                    DriverStation.MatchType.Practice -> MatchType.PRACTICE
                    DriverStation.MatchType.None -> MatchType.NONE
                },
                DriverStation.getMatchNumber(),
                DriverStation.getReplayNumber()
        )
    override val gameSpecificMessage: String
        get() = DriverStation.getGameSpecificMessage()

    override val controlWord: ControlWord
        get() = ControlWord(DriverStation.isEnabled(), DriverStation.isAutonomous(), DriverStation.isTest(), DriverStation.isEStopped(), DriverStation.isFMSAttached(), DriverStation.isDSAttached())
    override val driverStationLocationValue: Int
        get() = DriverStation.getLocation()
    override val matchTime: Double?
        get() {
            val r = DriverStation.getMatchTime()
            if(r == -1.0){
                return null
            }
            return r
        }

}
