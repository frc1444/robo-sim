package com.first1444.sim.gdx

import java.io.Closeable

interface CloseableUpdateable : Closeable {
    fun update(delta: Float)
    companion object {
        @JvmStatic
        fun fromUpdateable(updateable: Updateable) = object : CloseableUpdateable {
            override fun update(delta: Float) {
                updateable.update(delta)
            }
            override fun close() {
            }
        }
    }
}
