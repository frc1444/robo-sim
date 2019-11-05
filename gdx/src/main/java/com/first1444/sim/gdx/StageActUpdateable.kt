package com.first1444.sim.gdx

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage

class StageActUpdateable
@JvmOverloads constructor(
        private val stage: Stage,
        private val setAsInputProcessor: Boolean = false
) : Updateable {
    override fun update(delta: Float) {
        stage.act(delta)
        if(setAsInputProcessor){
            Gdx.input.inputProcessor = stage
        }
    }

}
