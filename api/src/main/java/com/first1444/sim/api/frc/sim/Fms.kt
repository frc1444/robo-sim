package com.first1444.sim.api.frc.sim

import com.first1444.sim.api.frc.FrcMode
import com.first1444.sim.api.frc.MatchInfo

interface Fms {
    val mode: FrcMode
    val matchInfo: MatchInfo

    /**
     * The amount of time left in the current [FrcMode] in seconds
     */
    val matchTime: Double
}
