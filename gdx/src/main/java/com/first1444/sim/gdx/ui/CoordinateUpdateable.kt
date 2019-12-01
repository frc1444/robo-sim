package com.first1444.sim.gdx.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.first1444.sim.gdx.Updateable
import com.first1444.sim.gdx.gdxVector
import java.text.DecimalFormat

class CoordinateUpdateable(
        private val uiStage: Stage,
        private val gameStage: Stage
) : Updateable {
    companion object {
        private val FORMAT = DecimalFormat(" #0.000;-#0.000")
    }
    private val group = Table().apply {
        setFillParent(true)
    }
    private val xLabel: Label
    private val yLabel: Label

    init {
        val table = Table()

        val skin = Skin(Gdx.files.classpath("skins/scoreboard/skin.json"))
        val font = skin.getFont("default")
        group.addActor(table)
        table.apply {
            setFillParent(true)
            right()
            top()
            val labelStyle = Label.LabelStyle(font, skin.getColor("score"))
            xLabel = Label("text", labelStyle)
            yLabel = Label("text", labelStyle)
            add(Label("x", labelStyle))
            add(Label("y", labelStyle))
            row()
            add(xLabel).width(150f).center()
            add(yLabel).width(150f).center()
        }
    }

    override fun update(delta: Float) {
        uiStage.addActor(group)
        val x = Gdx.input.getX(0)
        val y = Gdx.input.getY(0)
        val position = gameStage.viewport.unproject(gdxVector(x.toFloat(), y.toFloat()))
        xLabel.setText(FORMAT.format(position.x))
        yLabel.setText(FORMAT.format(position.y))
    }

}
