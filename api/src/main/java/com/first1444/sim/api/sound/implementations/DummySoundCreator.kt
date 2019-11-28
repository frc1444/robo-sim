package com.first1444.sim.api.sound.implementations

import com.first1444.sim.api.sound.Sound
import com.first1444.sim.api.sound.SoundCreator

object DummySoundCreator : SoundCreator {
    override fun create(string: String): Sound = DummySound
    override fun close() {
    }
}
