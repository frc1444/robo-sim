package com.first1444.sim.gdx

import java.io.Closeable

interface CloseableUpdateable : Closeable {
    fun update(delta: Float)
    companion object {
        @Deprecated("Use fromUpdateOnly")
        @JvmStatic
        fun fromUpdateable(updateable: Updateable) = object : CloseableUpdateable {
            override fun update(delta: Float) {
                updateable.update(delta)
            }
            override fun close() {
            }
        }
        @JvmStatic inline fun fromUpdateOnly(crossinline lambda: (Float) -> Unit) = object : CloseableUpdateable {
            override fun update(delta: Float) = lambda(delta)
            override fun close() {
            }
        }
        @JvmStatic
        fun fromCloseable(closeable: Closeable) = object : CloseableUpdateable {
            override fun update(delta: Float) {
            }

            override fun close() {
                closeable.close()
            }
        }
        @JvmSynthetic
        inline fun fromCloseable(crossinline closeable: () -> Unit) = object : CloseableUpdateable {
            override fun update(delta: Float) {
            }

            override fun close() {
                closeable()
            }
        }
    }
}
