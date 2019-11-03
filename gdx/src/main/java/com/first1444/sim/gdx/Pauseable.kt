package com.first1444.sim.gdx

interface Pauseable {
    fun pause()
    fun resume()
    companion object {
        @JvmStatic
        val NOTHING = object : Pauseable {
            override fun pause() {
            }
            override fun resume() {
            }
        }
    }
}
