package com.first1444.sim.gdx.render

import com.badlogic.gdx.scenes.scene2d.Stage
import com.first1444.sim.gdx.Renderable

class StageRenderable(
        private val stage: Stage
) : Renderable {

    override fun render(delta: Float) {
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    override fun dispose() {
        stage.dispose()
    }

}
