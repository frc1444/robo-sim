package com.first1444.sim.gdx.init

import com.badlogic.gdx.Screen

interface ScreenChanger {
    fun change(screen: Screen)

    companion object {
        @JvmSynthetic
        operator fun invoke(lambda: (Screen) -> Unit): ScreenChanger {
            return object : ScreenChanger {
                override fun change(screen: Screen) {
                    lambda(screen)
                }
            }
        }
    }
}
