package com.first1444.sim.gdx.sound

import com.first1444.sim.api.sound.ActiveSound
import com.first1444.sim.api.sound.Sound

class GdxSound(
        private val sound: com.badlogic.gdx.audio.Sound
): Sound, AutoCloseable {
    override fun play(): ActiveSound {
        val id = sound.play()
        return GdxActiveSound(sound, id)
    }

    override fun close() {
        sound.dispose()
    }

}
