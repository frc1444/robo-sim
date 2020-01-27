package com.first1444.sim.gdx

import com.badlogic.gdx.ScreenAdapter

class SimpleScreen
@JvmOverloads constructor(
    private val updateable: CloseableUpdateable,
    private val renderable: Renderable,
    private val pauseable: Pauseable = Pauseable.NOTHING
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
        updateable.close()
        renderable.dispose()
        println("SimpleScreen dispose!")
    }
}
