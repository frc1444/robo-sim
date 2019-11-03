package com.first1444.sim.api.frc

data class MatchInfo(
        val eventName: String,
        val matchType: MatchType?,
        val matchNumber: Int,
        val replayNumber: Int
)
