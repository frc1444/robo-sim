package com.first1444.sim.api.frc

/**
 * A common interface for things that communicate things available from the FMS
 */
interface Fms : FrcModeProvider {
    val matchInfo: MatchInfo

    /**
     * The amount of time left in the current [FrcMode] in seconds
     */
    val matchTime: Double?
}
