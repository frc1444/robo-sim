package com.first1444.sim.gdx.init

import com.badlogic.gdx.Screen

interface ScreenCreator {
    fun create(changer: ScreenChanger): Screen
}
