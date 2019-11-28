package com.first1444.sim.api.sound

interface ActiveSound {
    fun stop()

    val canCheckIsPlaying: Boolean
    val isPlaying: Boolean

    val isPauseable: Boolean
    fun pause()
    fun resume()
}
