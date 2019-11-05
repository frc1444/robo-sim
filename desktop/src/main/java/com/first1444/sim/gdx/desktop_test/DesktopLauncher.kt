package com.first1444.sim.gdx.desktop_test

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = Lwjgl3ApplicationConfiguration()
        Lwjgl3Application(createScreen(), config)
    }
}
