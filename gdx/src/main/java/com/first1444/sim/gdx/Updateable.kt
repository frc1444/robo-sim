package com.first1444.sim.gdx

@Deprecated("Use CloseableUpdateable")
interface Updateable {
    fun update(delta: Float)

    companion object {
        @JvmSynthetic
        inline operator fun invoke(crossinline lambda: (Float) -> Unit): Updateable{
            return object : Updateable {
                override fun update(delta: Float) {
                    lambda(delta)
                }
            }
        }
        @JvmStatic
        fun wrap(runnable: Runnable): Updateable = Updateable { runnable.run() }
    }
}
