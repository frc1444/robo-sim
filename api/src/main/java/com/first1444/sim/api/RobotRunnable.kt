package com.first1444.sim.api

interface RobotRunnable : Runnable, AutoCloseable {
    companion object {
        @JvmStatic
        inline fun wrap(crossinline runnable: () -> Unit): RobotRunnable {
            return object : RobotRunnable {
                override fun run() {
                    runnable()
                }

                override fun close() {
                }
            }
        }
    }
}
