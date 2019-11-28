package com.first1444.sim.gdx.sound

import com.badlogic.gdx.audio.Sound
import com.first1444.sim.api.sound.ActiveSound

class GdxActiveSound(
        private val sound: Sound,
        private val id: Long
) : ActiveSound {
    override fun stop() {
        sound.stop(id)
    }

    override val canCheckIsPlaying: Boolean
        get() = false
    override val isPlaying: Boolean
        get() = false
    override val isPauseable: Boolean
        get() = true

    override fun pause() {
        sound.pause(id)
    }

    override fun resume() {
        sound.resume(id)
    }

}
