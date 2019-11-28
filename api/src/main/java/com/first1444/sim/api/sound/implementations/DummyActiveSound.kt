package com.first1444.sim.api.sound.implementations

import com.first1444.sim.api.sound.ActiveSound

object DummyActiveSound : ActiveSound {
    override fun stop() {
    }

    override val canCheckIsPlaying: Boolean
        get() = false
    override val isPlaying: Boolean
        get() = false
    override val isPauseable: Boolean
        get() = false

    override fun pause() {
        throw UnsupportedOperationException()
    }
    override fun resume() {
        throw UnsupportedOperationException()
    }

}
