package com.first1444.sim.api.frc.sim

import com.first1444.sim.api.frc.Fms
import com.first1444.sim.api.frc.FrcMode
import com.first1444.sim.api.frc.MatchInfo

class DSFmsSimulator(
        startingMatchInfo: MatchInfo
) : Fms {


    override var mode: FrcMode = FrcMode.DISABLED
        set(value) {
            val current = field
            if(current == value){
                return
            }
            check(current == FrcMode.DISABLED || value == FrcMode.DISABLED) { "You cannot jump between enabled modes! You must transition to disabled first!" }
            field = value
        }

    override var matchInfo: MatchInfo = startingMatchInfo

    override val matchTime: Double? = null



}
