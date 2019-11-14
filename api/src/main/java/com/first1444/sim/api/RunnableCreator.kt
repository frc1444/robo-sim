package com.first1444.sim.api

interface RunnableCreator {
    /**
     * In the WPI implementation, this should be called when the constructor of the Robot class is called
     */
    fun prematureInit()

    /**
     * In the WPI implementation, this should be called when robotInit() is called
     */
    fun createRunnable(): RobotRunnable

    companion object {
        @JvmStatic
        fun wrap(creator: () -> RobotRunnable): RunnableCreator{
            return object : RunnableCreator {
                override fun prematureInit() {
                }

                override fun createRunnable(): RobotRunnable {
                    return creator()
                }

            }
        }
    }
}
