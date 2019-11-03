package com.first1444.sim.gdx

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.first1444.sim.api.frc.Alliance
import com.first1444.sim.api.frc.DriverStationLocation
import com.first1444.sim.gdx.render.RenderableMultiplexer
import com.first1444.sim.gdx.render.ResetRenderable
import com.first1444.sim.gdx.render.StageRenderable
import com.first1444.sim.gdx.ui.UIViewport

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
        return SimpleScreen(Updateable {}, RenderableMultiplexer(listOf(
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
