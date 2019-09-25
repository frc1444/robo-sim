package com.first1444.sim.gdx

import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import org.junit.jupiter.api.Test
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration

class GuiTest {
    private class Gui(screen: Screen) : Game() {
        init {
            this.screen = screen
        }
        override fun create() {
        }
    }
    @Test
    fun test(){
        println("Hello")
		Lwjgl3Application(
                Gui(ConfigScreen()),
                Lwjgl3ApplicationConfiguration().apply {

                }
        );
    }
}
