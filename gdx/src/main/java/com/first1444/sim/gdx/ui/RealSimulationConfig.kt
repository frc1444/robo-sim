package com.first1444.sim.gdx.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.first1444.sim.api.frc.Alliance
import com.first1444.sim.api.frc.DriverStationLocation
import com.first1444.sim.api.frc.sim.MutableFrcDriverStation
import com.first1444.sim.gdx.SimpleScreen
import com.first1444.sim.gdx.Updateable
import com.first1444.sim.gdx.clickUpListener
import com.first1444.sim.gdx.render.RenderableMultiplexer
import com.first1444.sim.gdx.render.ResetRenderable
import com.first1444.sim.gdx.render.StageRenderable

object RealSimulationConfig {
    @JvmSynthetic
    fun createScreen(uiSkin: Skin, finishListener: (config: Config) -> Unit): Screen = createScreen(uiSkin, object : FinishListener {
        override fun finished(config: Config) {
            finishListener(config)
        }
    })

    @JvmStatic
    fun createScreen(uiSkin: Skin, finishListener: FinishListener): Screen {
        val stage = Stage(UIViewport(640f))
        val table = Table()
        table.setFillParent(true)
        stage.addActor(table)
        val driverStation = MutableFrcDriverStation()
        driverStation.alliance = Alliance.RED
        DriverStationConfig.populateTable(table, driverStation, uiSkin)
        table.add(TextButton("done", uiSkin).apply {
            addListener(clickUpListener {
                finishListener.finished(Config(driverStation.driverStationLocation, driverStation.alliance ?: Alliance.RED, driverStation.gameSpecificMessage))
            })
        })
        return SimpleScreen(Updateable {
            stage.act(it)
            Gdx.input.inputProcessor = stage
        }, RenderableMultiplexer(listOf(
                ResetRenderable(Color.BLACK),
                StageRenderable(stage)
        )))
    }
    class Config(
            val driverStationLocation: DriverStationLocation,
            val alliance: Alliance,
            val gameSpecificMessage: String
    )
    interface FinishListener {
        fun finished(config: Config)
    }
}
