package com.first1444.sim.gdx

import com.badlogic.gdx.ScreenAdapter

class SimpleScreen(
    private val updateable: Updateable,
    private val renderable: Renderable,
    private val pauseable: Pauseable
) : ScreenAdapter() {

    override fun render(delta: Float) {
        updateable.update(delta)
        renderable.render(delta)
    }

    override fun resize(width: Int, height: Int) {
        renderable.resize(width, height)
    }

    override fun pause() {
        pauseable.pause()
    }

    override fun resume() {
        pauseable.resume()
    }

    override fun dispose() {
        renderable.dispose()
    }
}
