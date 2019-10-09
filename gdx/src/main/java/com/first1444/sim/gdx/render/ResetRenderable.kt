package com.first1444.sim.gdx.render

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.first1444.sim.gdx.Renderable

class ResetRenderable(
        private val backgroundColor: Color
) : Renderable {
    override fun render(delta: Float) {
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }

    override fun resize(width: Int, height: Int) {
    }
    override fun dispose() {
    }

}
