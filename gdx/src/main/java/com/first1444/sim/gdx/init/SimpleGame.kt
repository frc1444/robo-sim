package com.first1444.sim.gdx.init

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen

class SimpleGame(
    private val screenCreator: ScreenCreator
) : ApplicationListener, ScreenChanger {

    private var newScreen: Screen? = null
    private var screen: Screen? = null

    constructor(screenCreator: (changer: ScreenChanger) -> Screen) : this(object : ScreenCreator {
        override fun create(changer: ScreenChanger): Screen = screenCreator(changer)
    })

    override fun change(screen: Screen) {
        newScreen = screen
    }

    private fun setScreen(screen: Screen) {
        val currentScreen = this.screen
        if (currentScreen != null) {
            currentScreen.hide()
            currentScreen.dispose()
        }
        this.screen = screen
        screen.show()
        screen.resize(Gdx.graphics.width, Gdx.graphics.height)
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
        screen?.render(Gdx.graphics.deltaTime)
    }

    override fun pause() {
        screen?.pause()
    }

    override fun resume() {
        screen?.resume()
    }

    override fun resize(width: Int, height: Int) {
        screen?.resize(width, height)
    }

    override fun dispose() {
        screen?.dispose()
    }
}
