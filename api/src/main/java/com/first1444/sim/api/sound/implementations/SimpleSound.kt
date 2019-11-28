package com.first1444.sim.api.sound.implementations

import com.first1444.sim.api.sound.ActiveSound
import com.first1444.sim.api.sound.Sound

@JvmSynthetic
fun SimpleSound(play: () -> Unit) = SimpleSound(Runnable(play))

class SimpleSound(
        private val playRunnable: Runnable
) : Sound {
    override fun play(): ActiveSound {
        playRunnable.run()
        return DummyActiveSound
    }

}
