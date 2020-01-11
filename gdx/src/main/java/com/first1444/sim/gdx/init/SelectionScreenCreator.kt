package com.first1444.sim.gdx.init

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.first1444.sim.gdx.CloseableUpdateable
import com.first1444.sim.gdx.SimpleScreen
import com.first1444.sim.gdx.StageActUpdateable
import com.first1444.sim.gdx.render.RenderableMultiplexer
import com.first1444.sim.gdx.render.ResetRenderable
import com.first1444.sim.gdx.render.StageRenderable
import com.first1444.sim.gdx.ui.ScreenSelector
import com.first1444.sim.gdx.ui.UIViewport

class SelectionScreenCreator(
        private val uiSkin: Skin,
        private val practiceScreenCreator: ScreenCreator?,
        private val realScreenCreator: ScreenCreator?,
        private val supplementaryScreenCreator: ScreenCreator? = null
) : ScreenCreator {
    override fun create(changer: ScreenChanger): Screen {

        val stage = Stage(UIViewport(640f))
        val table = Table()
        table.setFillParent(true)
        table.add(Label("Robot Simulator", uiSkin))
        table.row()
        val selectors = mutableListOf<ScreenSelector.ScreenSelect>()
        if(practiceScreenCreator != null){
            selectors.add(ScreenSelector.ScreenSelect("Practice") {
                changer.change(practiceScreenCreator.create(changer))
            })
        }
        if(realScreenCreator != null){
            selectors.add(ScreenSelector.ScreenSelect("Real") {
                changer.change(realScreenCreator.create(changer))
            })
        }
        if(supplementaryScreenCreator != null){
            selectors.add(ScreenSelector.ScreenSelect("Supplementary") {
                changer.change(supplementaryScreenCreator.create(changer))
            })
        }
        ScreenSelector.populateTable(table, uiSkin, selectors)
        table.add(Label("Made by Joshua Shannon from Lightning 1444", uiSkin))

        stage.addActor(table)
        return SimpleScreen(CloseableUpdateable.fromUpdateable(StageActUpdateable(stage, true)), RenderableMultiplexer(listOf(ResetRenderable(Color.BLACK), StageRenderable(stage))))
    }

}
