package com.first1444.sim.gdx.desktop_test

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.controllers.Controllers
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.first1444.sim.gdx.init.*

private fun createSelectionCreator(uiSkin: Skin, changer: ScreenChanger): ScreenCreator {
    Controllers.getControllers() // initialize controllers ASAP
    val creator = MyRobotCreator
    val exitButtonUpdateableCreator = ExitButtonCreator(Runnable {
        changer.change(createSelectionCreator(uiSkin, changer).create(changer))
    })
    return SelectionScreenCreator(
            uiSkin,
            FieldScreenCreator(uiSkin, UpdateableCreatorMultiplexer(listOf(
                    PracticeUpdateableCreator(creator),
                    Field2019Creator,
                    exitButtonUpdateableCreator
            ))),
            RealConfigScreenCreator(uiSkin) { _, config: RealConfig ->
                changer.change(FieldScreenCreator(uiSkin, UpdateableCreatorMultiplexer(listOf(
                        RealUpdateableCreator(uiSkin, config, creator),
                        Field2019Creator,
                        exitButtonUpdateableCreator
                ))).create(changer))
            }
    )
}

fun createScreen(): ApplicationListener {
    return SimpleGame { changer ->
        val uiSkin = Skin(Gdx.files.classpath("skins/sgx/sgx-ui.json"))
        createSelectionCreator(uiSkin, changer).create(changer)
    }
}
