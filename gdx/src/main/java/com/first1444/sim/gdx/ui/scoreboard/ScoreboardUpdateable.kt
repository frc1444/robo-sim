package com.first1444.sim.gdx.ui.scoreboard

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.first1444.sim.api.frc.Fms
import com.first1444.sim.gdx.CloseableUpdateable
import kotlin.math.roundToInt

class ScoreboardUpdateable(
        private val stage: Stage,
        private val fms: Fms
) : CloseableUpdateable {
    private val skin = Skin(Gdx.files.classpath("skins/scoreboard/skin.json"))
    private val group = Table().apply {
        setFillParent(true)
    }
    private val label: Label

    init {
        val scoreboardTable = Table()
        val font = skin.getFont("default")
        group.addActor(scoreboardTable)
        scoreboardTable.apply {
            setFillParent(true)
            center()
            top()
            label = Label("text", Label.LabelStyle(font, skin.getColor("score")))
            add(label)
        }
    }
    override fun update(delta: Float) {
        stage.addActor(group)
        val timeLeft = fms.matchTime
        if(timeLeft != null){
            label.setText(fms.mode.name + " " + timeLeft.roundToInt().toString())
        } else {
            label.setText(fms.mode.name)
        }
    }

    override fun close() {
        skin.dispose()
    }

}
