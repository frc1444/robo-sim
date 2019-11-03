package com.first1444.sim.api.frc.sim

import com.first1444.sim.api.Clock
import com.first1444.sim.api.frc.Fms
import com.first1444.sim.api.frc.FrcMode
import com.first1444.sim.api.frc.MatchInfo

class MatchFmsSimulator(
        private val clock: Clock,
        startingMatchInfo: MatchInfo,
        private val teleopTransitionTime: Double = .5
) : Fms {
    private var startTime: Double? = null
    /**
     * Although you are able to set this whenever you want, it is recommended to do so through [start]
     */
    override var matchInfo: MatchInfo = startingMatchInfo

    /**
     * Starts the simulation
     * @param newMatchInfo If not null, this sets [matchInfo]
     */
    fun start(newMatchInfo: MatchInfo?){
        startTime = clock.timeSeconds
        if(newMatchInfo != null){
            matchInfo = newMatchInfo
        }
    }
    fun stop(){
        startTime = null
    }

    val isMatchActive: Boolean
        get() {
            val startTime = this.startTime ?: return false
            return startTime <= 150
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

    override val matchTime: Double?
        get() {
            val startTime = this.startTime ?: return null
            val elapsed = clock.timeSeconds - startTime
            val transition = teleopTransitionTime
            return when {
                elapsed > 150 + transition -> null // we're disabled
                elapsed > 15 + transition -> 150 - elapsed
                elapsed > 15 -> null // we're disabled
                else -> 15 - elapsed
            }
        }

}
