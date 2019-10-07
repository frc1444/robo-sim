package com.first1444.sim.gdx.render

import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.first1444.sim.gdx.Renderable
import com.first1444.sim.gdx.WorldManager

class WorldDebugRenderable(
        private val worldManager: WorldManager
) : Renderable {

    private val debugRenderer = Box2DDebugRenderer()

    override fun render(delta: Float) {
        debugRenderer.render(worldManager.world, worldManager.viewport.camera.combined)
    }

    override fun resize(width: Int, height: Int) {
        worldManager.viewport.update(width, height, false)
    }

    override fun dispose() {
        debugRenderer.dispose()
    }

}
