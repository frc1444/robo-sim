package com.first1444.sim.api

interface RobotRunnable : Runnable, AutoCloseable {
    companion object {
        fun wrap(runnable: () -> Unit): RobotRunnable {
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
