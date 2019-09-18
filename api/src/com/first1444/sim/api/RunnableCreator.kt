package com.first1444.sim.api

interface RunnableCreator {
    /**
     */
    fun prematureInit()
    fun createRunnable(): Runnable
}
