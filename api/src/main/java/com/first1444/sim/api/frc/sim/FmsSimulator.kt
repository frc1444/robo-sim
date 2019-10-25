package com.first1444.sim.api.frc.sim

import com.first1444.sim.api.Clock
import com.first1444.sim.api.frc.FrcMode
import com.first1444.sim.api.frc.MatchInfo

class FmsSimulator(
        private val clock: Clock,
        startingMatchInfo: MatchInfo,
        private val teleopTransitionTime: Double = .5
) : Fms {
    private var startTime: Double? = null
    private var myMatchInfo: MatchInfo = startingMatchInfo

    fun start(newMatchInfo: MatchInfo?){
        startTime = clock.timeSeconds
        if(newMatchInfo != null){
            myMatchInfo = newMatchInfo
        }
    }
    fun stop(){
        startTime = null
    }

    override val mode: FrcMode
        get() {
            val startTime = this.startTime ?: return FrcMode.DISABLED
            val elapsed = clock.timeSeconds - startTime
            val transition = teleopTransitionTime
            return when {
                elapsed > 150 + transition -> FrcMode.DISABLED
                elapsed > 15 + transition -> FrcMode.TELEOP
                elapsed > 15 -> FrcMode.DISABLED
                else -> FrcMode.AUTONOMOUS
            }
        }

    override val matchInfo: MatchInfo
        get() = myMatchInfo
    override val matchTime: Double
        get() {
            val startTime = this.startTime ?: return 0.0
            val elapsed = clock.timeSeconds - startTime
            val transition = teleopTransitionTime
            return when {
                elapsed > 150 + transition -> 0.0
                elapsed > 15 + transition -> 150 - elapsed
                elapsed > 15 -> 0.0 // we're disabled
                else -> 15 - elapsed
            }
        }

}
