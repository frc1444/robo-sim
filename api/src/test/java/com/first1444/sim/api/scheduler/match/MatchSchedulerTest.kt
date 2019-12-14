package com.first1444.sim.api.scheduler.match

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class MatchSchedulerTest {

    @Test
    fun testMatchScheduler() {
        val timeGetter = DummyTimeGetter()
        timeGetter.currentMode = MatchTime.Mode.AUTONOMOUS
        val value = intArrayOf(0)
        val scheduler = DefaultMatchScheduler(timeGetter)
        scheduler.schedule(MatchTime(1.0, MatchTime.Mode.AUTONOMOUS, MatchTime.Type.AFTER_START)){ value[0]++ }
        timeGetter.timestamp = 0.0
        scheduler.run()
        Assertions.assertEquals(0, value[0])
        timeGetter.timestamp = .5
        scheduler.run()
        Assertions.assertEquals(0, value[0])
        timeGetter.timestamp = 1.0
        scheduler.run()
        Assertions.assertEquals(1, value[0])
        scheduler.run()
        Assertions.assertEquals(1, value[0])
        scheduler.schedule(MatchTime(1.0, MatchTime.Mode.AUTONOMOUS, MatchTime.Type.FROM_END)){ value[0]++ }
        scheduler.run()
        Assertions.assertEquals(1, value[0])
        timeGetter.timestamp = 13.0
        scheduler.run()
        Assertions.assertEquals(1, value[0])
        timeGetter.timestamp = 14.0
        scheduler.run()
        Assertions.assertEquals(2, value[0])
        timeGetter.currentMode = null
        scheduler.run()
        timeGetter.currentMode = MatchTime.Mode.TELEOP
        timeGetter.timestamp = 1000.0
        scheduler.schedule(MatchTime(5.0, MatchTime.Mode.TELEOP, MatchTime.Type.FROM_END)){ value[0]++ }
        scheduler.run()
        Assertions.assertEquals(2, value[0])
        timeGetter.timestamp = 1129.0
        scheduler.run()
        Assertions.assertEquals(2, value[0])
        timeGetter.timestamp = 1130.0
        scheduler.run()
        Assertions.assertEquals(3, value[0])
    }

    @Test
    fun testTeleopSchedulingInAuto() {
        val timeGetter = DummyTimeGetter()
        timeGetter.currentMode = MatchTime.Mode.AUTONOMOUS
        val value = intArrayOf(0)
        val scheduler = DefaultMatchScheduler(timeGetter)
        scheduler.schedule(MatchTime(1.0, MatchTime.Mode.TELEOP, MatchTime.Type.AFTER_START)) { value[0]++ }// TODO make this from end
        timeGetter.timestamp = 0.0
        scheduler.run()
        Assertions.assertEquals(0, value[0])
        timeGetter.timestamp = .5
        scheduler.run()
        Assertions.assertEquals(0, value[0])
        timeGetter.timestamp = 1.0
        scheduler.run()
        Assertions.assertEquals(0, value[0])
    }

    internal class DummyTimeGetter : DefaultMatchScheduler.TimeGetter {
        override var currentMode: MatchTime.Mode? = null
        override var timestamp = 0.0

        override fun getRemainingTimeInPeriod(modeStartTimestamp: Double): Double? {
            val mode = currentMode ?: return null
            return when (mode) {
                MatchTime.Mode.TELEOP -> modeStartTimestamp + 135 - timestamp
                MatchTime.Mode.AUTONOMOUS -> modeStartTimestamp + 15 - timestamp
                else -> throw UnsupportedOperationException("Unsupported mode: $mode")
            }
        }

        override fun getTimeInPeriod(modeStartTimestamp: Double): Double {
            return timestamp - modeStartTimestamp
        }

        override val isAutonomous: Boolean
            get() = currentMode === MatchTime.Mode.AUTONOMOUS

        override val isTeleop: Boolean
            get() = currentMode === MatchTime.Mode.TELEOP

    }
}
