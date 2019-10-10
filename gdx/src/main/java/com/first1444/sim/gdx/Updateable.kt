package com.first1444.sim.gdx

interface Updateable {
    fun update(delta: Float)

    companion object {
        operator fun invoke(lambda: (Float) -> Unit): Updateable{
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
