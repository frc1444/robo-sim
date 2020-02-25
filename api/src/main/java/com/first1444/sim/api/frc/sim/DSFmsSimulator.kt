package com.first1444.sim.api.frc.sim

import com.first1444.sim.api.frc.ControlWord
import com.first1444.sim.api.frc.Fms
import com.first1444.sim.api.frc.FrcMode
import com.first1444.sim.api.frc.MatchInfo

class DSFmsSimulator(
        startingMatchInfo: MatchInfo
) : Fms {
    override var controlWord: ControlWord = ControlWord(false, false, false, false, false, false)

    override var matchInfo: MatchInfo = startingMatchInfo

    override val matchTime: Double? = null



}
