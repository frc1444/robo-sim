package com.first1444.sim.api.sound.implementations

import com.first1444.sim.api.sound.ActiveSound
import com.first1444.sim.api.sound.Sound
import java.util.*

class RandomSoundMultiplexer(
        private val sounds: List<Sound>
) : Sound {
    private val random = Random()
    override fun play(): ActiveSound {
        val index = random.nextInt(sounds.size)
        return sounds[index].play()
    }

}
