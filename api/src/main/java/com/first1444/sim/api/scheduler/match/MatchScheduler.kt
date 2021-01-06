package com.first1444.sim.api.scheduler.match


interface MatchScheduler {
    fun schedule(startTime: MatchTime, runnable: Runnable)
}
