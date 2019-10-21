package com.first1444.sim.gdx.desktop_test

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.controllers.Controllers

class ControllerTest : ApplicationAdapter() {
    override fun create() {

    }

    override fun render() {
        println(Controllers.getControllers())
    }

}
