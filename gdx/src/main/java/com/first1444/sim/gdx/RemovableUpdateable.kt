package com.first1444.sim.gdx

interface RemovableUpdateable : Updateable {
    fun onRemove()

    companion object {
        @JvmStatic
        fun fromUpdateable(updateable: Updateable) = object : RemovableUpdateable {
            override fun onRemove() {
            }

            override fun update(delta: Float) {
                updateable.update(delta)
            }

            override fun close() {
                updateable.close()
            }

        }
    }
}
