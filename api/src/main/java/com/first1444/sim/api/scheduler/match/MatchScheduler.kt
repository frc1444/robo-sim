package com.first1444.sim.api.scheduler.match


interface MatchScheduler {
    fun schedule(startTime: MatchTime, runnable: Runnable)
}
@JvmSynthetic
fun MatchScheduler.schedule(startTime: MatchTime, runnable: () -> Unit){
    schedule(startTime, Runnable(runnable))
}
