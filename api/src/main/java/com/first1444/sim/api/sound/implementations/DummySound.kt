package com.first1444.sim.api.sound.implementations

import com.first1444.sim.api.sound.ActiveSound
import com.first1444.sim.api.sound.Sound

object DummySound : Sound {
    override fun play(): ActiveSound = DummyActiveSound
}
