package com.first1444.sim.api.scheduler.match

data class MatchTime(
        val time: Double,
        val mode: Mode,
        val type: Type
) {
    enum class Mode {
        AUTONOMOUS, TELEOP
    }
    enum class Type {
        AFTER_START, FROM_END
    }
}
