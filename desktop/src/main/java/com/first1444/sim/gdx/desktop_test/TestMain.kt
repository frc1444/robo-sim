package com.first1444.sim.gdx.desktop_test

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.controllers.Controllers
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.first1444.dashboard.bundle.DefaultDashboardBundle
import com.first1444.dashboard.wpi.NetworkTableInstanceBasicDashboard
import com.first1444.sim.api.frc.sim.DashboardFrcDriverStation
import com.first1444.sim.gdx.init.*
import edu.wpi.first.networktables.NetworkTableInstance

private fun createSelectionCreator(uiSkin: Skin, changer: ScreenChanger): ScreenCreator {
    Controllers.getControllers() // initialize controllers ASAP
    val networkTable = NetworkTableInstance.getDefault()
    // we still have to call either startServer() or startClient()
    val rootDashboard = NetworkTableInstanceBasicDashboard(networkTable)
    val bundle = DefaultDashboardBundle(rootDashboard)

    val creator = MyRobotCreator(bundle)
    val exitButtonUpdateableCreator = ExitButtonCreator(Runnable {
        changer.change(createSelectionCreator(uiSkin, changer).create(changer))
    })
    val fieldCreator = Field2020Creator

    return SelectionScreenCreator(
            uiSkin,
            FieldScreenCreator(uiSkin, UpdateableCreatorMultiplexer(listOf(
                    PracticeUpdateableCreator(creator),
                    fieldCreator,
                    exitButtonUpdateableCreator
            ))),
            RealConfigScreenCreator(uiSkin) { _, config: RealConfig ->
                changer.change(FieldScreenCreator(uiSkin, UpdateableCreatorMultiplexer(listOf(
                        RealUpdateableCreator(config, creator),
                        fieldCreator,
                        exitButtonUpdateableCreator
                ))).create(changer))
            },
            SupplementaryConfigScreenCreator(uiSkin, "localhost", listOf("localhost", "10.14.44.2")) { _, config ->
                val supplementaryCreator = MySupplementaryRobotCreator(config.hostAddress)
                changer.change(FieldScreenCreator(uiSkin, UpdateableCreatorMultiplexer(listOf(
                        SupplementaryUpdateableCreator(supplementaryCreator, DashboardFrcDriverStation(bundle.rootDashboard.getSubDashboard("FMSInfo"))),
                        fieldCreator,
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
