package com.first1444.sim.api

class RobotRunnableMultiplexer(
        private val robotRunnableList: List<@JvmWildcard RobotRunnable>
) : RobotRunnable {

    @Throws(Exception::class)
    override fun close() {
        for(robotRunnable in robotRunnableList){
            robotRunnable.close()
        }
    }
    override fun run() {
        for(robotRunnable in robotRunnableList){
            robotRunnable.run()
        }
    }

}
