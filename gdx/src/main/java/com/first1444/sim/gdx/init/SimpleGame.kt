package com.first1444.sim.gdx.init

import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen

class SimpleGame(
    private val screenCreator: ScreenCreator
) : Game(), ScreenChanger {
    private var newScreen: Screen? = null

    constructor(screenCreator: (changer: ScreenChanger) -> Screen) : this(object : ScreenCreator {
        override fun create(changer: ScreenChanger): Screen = screenCreator(changer)
    })

    override fun change(screen: Screen) {
        newScreen = screen
    }

    override fun create() {
        setScreen(screenCreator.create(this))
    }

    override fun render() {
        val newScreen = this.newScreen
        if(newScreen != null){
            setScreen(newScreen)
            this.newScreen = null
        }
        super.render()
    }

}