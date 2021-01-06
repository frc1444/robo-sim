package com.first1444.sim.gdx.init

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.first1444.sim.gdx.SimpleScreen
import com.first1444.sim.gdx.Updateable
import com.first1444.sim.gdx.clickUpListener
import com.first1444.sim.gdx.render.RenderableMultiplexer
import com.first1444.sim.gdx.render.ResetRenderable
import com.first1444.sim.gdx.render.StageRenderable
import com.first1444.sim.gdx.ui.UIViewport

/**
 * A simple [ScreenCreator] that can be used to create a screen to display options for a basic "supplementary" type program.
 */
class SupplementaryConfigScreenCreator(
        private val uiSkin: Skin,
        private val defaultHostAddress: String,
        private val quickSelectOptions: List<String>,
        private val finishListener: FinishListener
) : ScreenCreator {
    constructor(uiSkin: Skin, defaultHostAddress: String, finishListener: FinishListener) : this(uiSkin, defaultHostAddress, emptyList(), finishListener)

    constructor(uiSkin: Skin, defaultHostAddress: String, quickSelectOptions: List<String>, finishListener: (ScreenChanger, Config) -> Unit) : this(uiSkin, defaultHostAddress, quickSelectOptions, FinishListener(finishListener))
    constructor(uiSkin: Skin, defaultHostAddress: String, finishListener: (ScreenChanger, Config) -> Unit) : this(uiSkin, defaultHostAddress, FinishListener(finishListener))

    override fun create(changer: ScreenChanger): Screen {
        val stage = Stage(UIViewport(640f))
        val table = Table()
        table.setFillParent(true)
        stage.addActor(table)
        val textLabel = TextField(defaultHostAddress, uiSkin)
        table.add(Table().apply {
            add(textLabel)
            for(option in quickSelectOptions){
                add(TextButton(option, uiSkin).apply {
                    addListener(clickUpListener {
                        textLabel.text = option
                    })
                })
            }
            row()
        })
        table.row()
        table.add(TextButton("done", uiSkin).apply {
            addListener(clickUpListener {
                finishListener.finished(changer, Config(textLabel.text))
            })
        })
        return SimpleScreen(Updateable.fromUpdateOnly {
            stage.act(it)
            Gdx.input.inputProcessor = stage
        }, RenderableMultiplexer(listOf(
                ResetRenderable(Color.BLACK),
                StageRenderable(stage)
        )))
    }

    fun interface FinishListener {
        fun finished(changer: ScreenChanger, config: Config)
    }
    class Config(
        val hostAddress: String
    )
}
