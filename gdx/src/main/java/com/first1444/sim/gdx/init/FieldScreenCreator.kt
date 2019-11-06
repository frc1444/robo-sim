package com.first1444.sim.gdx.init

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.first1444.sim.api.Clock
import com.first1444.sim.gdx.*
import com.first1444.sim.gdx.implementations.deepspace2019.CargoEntity
import com.first1444.sim.gdx.implementations.deepspace2019.Field2019
import com.first1444.sim.gdx.render.RenderableMultiplexer
import com.first1444.sim.gdx.render.ResetRenderable
import com.first1444.sim.gdx.render.StageRenderable
import com.first1444.sim.gdx.render.WorldDebugRenderable
import com.first1444.sim.gdx.ui.UIViewport

class FieldScreenCreator(
        private val uiSkin: Skin,
        private val updateableCreator: UpdateableCreator
) : ScreenCreator {
    override fun create(changer: ScreenChanger): Screen {

        val clock = UpdateableClock()
        val viewport = object : ExtendViewport(10.0f, 20.0f) {
            override fun apply(centerCamera: Boolean) {
                super.apply(false)
            }
        }
        viewport.camera.position.set(0.0f, 0.0f, 0.0f)

        val worldManager = WorldManager()
        val contentStage = Stage(viewport)
        val uiStage = Stage(UIViewport(640f))

        val additionalUpdateable = updateableCreator.create(UpdateableCreator.Data(uiSkin, clock, uiStage, contentStage, worldManager))

        return SimpleScreen(
                UpdateableMultiplexer(listOf(
                        StageActUpdateable(uiStage, true),
                        clock,
                        additionalUpdateable,
                        worldManager
                )),
                RenderableMultiplexer(listOf(
                        ResetRenderable(Color.BLACK),
                        StageRenderable(contentStage),
                        WorldDebugRenderable(worldManager.world, viewport.camera),
                        StageRenderable(uiStage)
                ))
        )
    }

}
