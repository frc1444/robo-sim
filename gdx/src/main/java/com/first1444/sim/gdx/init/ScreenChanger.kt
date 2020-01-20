package com.first1444.sim.gdx.init

import com.badlogic.gdx.Screen

interface ScreenChanger {
    fun change(screen: Screen)

    companion object {
        @JvmSynthetic
        inline operator fun invoke(crossinline lambda: (Screen) -> Unit): ScreenChanger = object : ScreenChanger {
            override fun change(screen: Screen) {
                lambda(screen)
            }
        }
    }
}
