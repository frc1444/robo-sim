package com.first1444.sim.gdx


interface Updateable : AutoCloseable {
    fun update(delta: Float)
    companion object {
        @JvmField
        val UPDATE_NOTHING = object : Updateable {
            override fun update(delta: Float) {}
            override fun close() {}
        }
        @JvmStatic inline fun fromUpdateOnly(crossinline lambda: (Float) -> Unit) = object : Updateable {
            override fun update(delta: Float) = lambda(delta)
            override fun close() {
            }
        }
        @JvmStatic
        fun fromCloseable(closeable: AutoCloseable) = object : Updateable {
            override fun update(delta: Float) {
            }

            override fun close() {
                closeable.close()
            }
        }
        @JvmSynthetic
        inline fun fromCloseable(crossinline closeable: () -> Unit) = object : Updateable {
            override fun update(delta: Float) {
            }

            override fun close() {
                closeable()
            }
        }
    }
}
