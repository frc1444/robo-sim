package com.first1444.sim.gdx.desktop_test

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.EdgeShape
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.first1444.sim.gdx.*
import com.first1444.sim.gdx.render.RenderableMultiplexer
import com.first1444.sim.gdx.render.ResetRenderable
import com.first1444.sim.gdx.render.StageRenderable
import com.first1444.sim.gdx.render.WorldDebugRenderable

class TestMain : Game() {
    override fun create() {
        val clock = UpdateableClock()
        val viewport = object : ExtendViewport(14.0f, 14.0f) {
            override fun apply(centerCamera: Boolean) {
                super.apply(false)
            }
        }
        viewport.camera.position.set(0.0f, 0.0f, 0.0f)
//        viewport.apply(true) // by default, bottom left is now (0, 0)

        val worldManager = WorldManager()
        val contentStage = Stage(viewport)

        worldManager.world.createBody(BodyDef().apply{
            type = BodyDef.BodyType.DynamicBody
        }).createFixture(FixtureDef().apply {
            shape = PolygonShape().apply {
                setAsBox(0.5f, 0.5f, Vector2.Zero, 0.0f)
            }
        })

        setScreen(SimpleScreen(
                UpdateableMultiplexer(listOf(
                        clock,
                        worldManager
                )),
                RenderableMultiplexer(listOf(
                        ResetRenderable(Color.BLACK),
                        StageRenderable(contentStage),
                        WorldDebugRenderable(worldManager.world, viewport.camera)
                )),
                object : Pauseable {
                    override fun pause() {
                    }
                    override fun resume() {
                    }
                }
        ))
    }


}
