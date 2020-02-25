package com.first1444.sim.api.frc.sim

import com.first1444.sim.api.frc.*

/**
 * A [FrcDriverStation] implementation that is good for testing
 */
open class MutableFrcDriverStation : FrcDriverStation {
    override var alliance: Alliance? = Alliance.RED

    override var matchInfo: MatchInfo = MatchInfo("", MatchType.NONE, 0, 0)
    override var gameSpecificMessage: String = ""

    override var mode: FrcMode
        get() = super.mode
        set(value) {
            val previous = controlWord
            if(value == FrcMode.DISABLED){
                controlWord = ControlWord(false, previous.isAutonomous, previous.isTest, previous.isEmergencyStop, previous.isFMSAttached, previous.isDriverStationAttached)
            }
            controlWord = ControlWord(true, value == FrcMode.AUTONOMOUS, value == FrcMode.TEST, previous.isEmergencyStop, previous.isFMSAttached, previous.isDriverStationAttached)
        }
    override var controlWord: ControlWord = ControlWord.fromWord(0)
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
