package com.first1444.sim.api.frc

interface Fms {
    val mode: FrcMode
    val matchInfo: MatchInfo

    /**
     * The amount of time left in the current [FrcMode] in seconds
     */
    val matchTime: Double?
}
