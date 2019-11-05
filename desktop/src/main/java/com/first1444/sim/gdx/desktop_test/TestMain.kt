package com.first1444.sim.gdx.desktop_test

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.first1444.sim.gdx.init.*

fun createScreen(): Game {
    return SimpleGame(
            object : ScreenCreator {
                override fun create(changer: ScreenChanger): Screen {
                    val uiSkin = Skin(Gdx.files.classpath("skins/sgx/sgx-ui.json"))
                    val creator = MyRobotCreator()
                    return SelectionScreenCreator(
                            uiSkin,
                            FieldScreenCreator(uiSkin, PracticeUpdateableCreator(creator)),
                            RealConfigScreenCreator(uiSkin, object : RealConfigScreenCreator.FinishListener {
                                override fun finished(changer: ScreenChanger, config: RealConfigScreenCreator.Config) {
                                    changer.change(FieldScreenCreator(uiSkin, RealUpdateableCreator(uiSkin, config, creator)).create(changer))
                                }

                            })
                    ).create(changer)
                }

            }
    )
}
