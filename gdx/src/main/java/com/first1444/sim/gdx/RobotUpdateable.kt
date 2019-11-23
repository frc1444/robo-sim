package com.first1444.sim.gdx

import com.first1444.sim.api.RobotRunnable
import com.first1444.sim.api.RunnableCreator

class RobotUpdateable
/**
 * Handles [RunnableCreator.prematureInit] and [RunnableCreator.createRunnable]
 */
(
        private val runnableCreator: RunnableCreator
) : CloseableUpdateable {

    private var runnable: RobotRunnable? = null
    init {
        runnableCreator.prematureInit()
    }
    override fun update(delta: Float) {
        var runnable = this.runnable
        if(runnable == null){
            runnable = runnableCreator.createRunnable()
            this.runnable = runnable
        }
        runnable.run()
    }
    @Throws(Exception::class)
    override fun close() {
        runnable?.close()
    }

}
