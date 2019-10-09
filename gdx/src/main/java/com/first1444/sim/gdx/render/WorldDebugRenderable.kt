package com.first1444.sim.gdx.render

import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World
import com.first1444.sim.gdx.Renderable
import com.first1444.sim.gdx.WorldManager

class WorldDebugRenderable(
        private val world: World,
        private val camera: Camera
) : Renderable {

    private val debugRenderer = Box2DDebugRenderer()

    override fun render(delta: Float) {
        debugRenderer.render(world, camera.combined)
    }

    override fun resize(width: Int, height: Int) {
    }
    override fun dispose() {
        debugRenderer.dispose()
    }

}
