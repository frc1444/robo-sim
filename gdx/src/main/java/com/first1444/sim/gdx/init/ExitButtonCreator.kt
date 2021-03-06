package com.first1444.sim.gdx.init

import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.first1444.sim.gdx.Updateable
import com.first1444.sim.gdx.clickUpListener

class ExitButtonCreator(
        private val onClick: Runnable
) : UpdateableCreator {
    override fun create(data: UpdateableCreator.Data): Updateable {
        val stage = data.uiStage
        stage.addActor(Table().apply {
            setFillParent(true)
            top().left()
            add(TextButton("Exit", data.uiSkin).apply {
                addListener(clickUpListener {
                    onClick.run()
                })
            })
        })
        return object : Updateable {
            override fun update(delta: Float) {
            }
            override fun close() {
            }
        }
    }

}
