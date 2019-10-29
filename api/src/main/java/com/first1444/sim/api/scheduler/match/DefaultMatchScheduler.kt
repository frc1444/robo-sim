package com.first1444.sim.api.scheduler.match

import com.first1444.sim.api.Clock
import com.first1444.sim.api.frc.FrcDriverStation
import com.first1444.sim.api.frc.FrcMode
import java.util.*


class DefaultMatchScheduler(
        private val timeGetter: TimeGetter
) : MatchSchedulerRunnable {
    private val timeMap = HashMap<MatchTime, MutableSet<Runnable>>()

    private var modeStartTimestamp: Double? = null
    private var mode: MatchTime.Mode? = null

    constructor(driverStation: FrcDriverStation, clock: Clock) : this(DriverStationTimeGetter(driverStation, clock))

    override fun schedule(startTime: MatchTime, runnable: Runnable) {
        timeMap.getOrPut(startTime, ::mutableSetOf).add(runnable)
    }

    override fun run() {
        val currentMode = timeGetter.currentMode
        if (currentMode !== mode) {
            mode = currentMode
            modeStartTimestamp = timeGetter.timestamp
        }
        if (modeStartTimestamp == null) { // basically, set the start timestamp for the null mode (usually disabled)
            mode = null
            modeStartTimestamp = timeGetter.timestamp
        }
        doUpdate()
    }

    private fun doUpdate() {
        val modeStartTimestamp = this.modeStartTimestamp ?: error("The modeStartTimestamp cannot be null when this method is run!")
        val timeRemaining = timeGetter.getRemainingTimeInPeriod(modeStartTimestamp)
        val timeTotal = timeGetter.getTimeInPeriod(modeStartTimestamp)

        val currentMode = timeGetter.currentMode

        val it = timeMap.entries.iterator()
        while (it.hasNext()) {
            val entry = it.next()
            val matchTime = entry.key
            if (currentMode === MatchTime.Mode.TELEOP && matchTime.mode !== MatchTime.Mode.TELEOP) { // if it should have already been ran
                runAll(entry.value)
                it.remove()
            } else if (currentMode === matchTime.mode) {
                when (matchTime.type) {
                    MatchTime.Type.FROM_END -> if (timeRemaining != null && matchTime.time >= timeRemaining) { // If the time has passed in the current mode
                        runAll(entry.value)
                        it.remove()
                    }
                    MatchTime.Type.AFTER_START -> if (matchTime.time <= timeTotal) {
                        runAll(entry.value)
                        it.remove()
                    }
                }
            }
        }
    }
    private fun runAll(runnables: Set<Runnable>){
        for(runnable in runnables){
            runnable.run()
        }
    }

    interface TimeGetter {
        /** @return A timestamp that increases over time. In seconds.
         */
        val timestamp: Double

        val isAutonomous: Boolean
        val isTeleop: Boolean

        val currentMode: MatchTime.Mode?
            get() {
                if (isAutonomous) {
                    return MatchTime.Mode.AUTONOMOUS
                } else if (isTeleop) {
                    return MatchTime.Mode.TELEOP
                }
                return null
            }

        /** @return If known, returns the amount of time remaining in the current mode/period in seconds. Otherwise returns null.
         */
        fun getRemainingTimeInPeriod(modeStartTimestamp: Double): Double?

        /** @return The amount of time that's passed in the current mode/period in seconds
         */
        fun getTimeInPeriod(modeStartTimestamp: Double): Double
    }

    class DriverStationTimeGetter(
            private val driverStation: FrcDriverStation,
            private val clock: Clock
    ) : TimeGetter {

        override val timestamp: Double
            get() = clock.timeSeconds

        override val isAutonomous: Boolean
            get() = driverStation.mode == FrcMode.AUTONOMOUS

        override val isTeleop: Boolean
            get() = driverStation.mode == FrcMode.TELEOP

        override fun getRemainingTimeInPeriod(modeStartTimestamp: Double): Double? {
            return driverStation.matchTime
        }

        override fun getTimeInPeriod(modeStartTimestamp: Double): Double {
            val remainingTime = getRemainingTimeInPeriod(modeStartTimestamp)
            if (remainingTime != null) {
                if (isAutonomous) {
                    return 15 - remainingTime
                } else if (isTeleop) {
                    return 135 - remainingTime
                }
                return 0.0
            }
            return timestamp - modeStartTimestamp
        }

    }
}
