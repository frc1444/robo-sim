package com.first1444.sim.gdx

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input

class KeyPressStopUpdateable(
        private val runnable: Runnable
) : Updateable {

    constructor(runnable: () -> Unit) : this(Runnable(runnable))

    override fun update(delta: Float) {
        // I decided not to include space bar here because I want to discourage the use of it. The amount of times someone presses space bar on the driver station...
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            runnable.run()
        }
    }

    override fun close() {
    }

}
