package com.first1444.sim.gdx.init

import com.first1444.sim.api.frc.FrcMode
import com.first1444.sim.api.frc.sim.MutableFrcDriverStation
import com.first1444.sim.gdx.*
import com.first1444.sim.gdx.ui.CoordinateUpdateable
import com.first1444.sim.gdx.ui.PracticeSimulation
import com.first1444.sim.gdx.ui.scoreboard.ScoreboardUpdateable

class PracticeUpdateableCreator(
        private val robotCreator: RobotCreator
) : UpdateableCreator {
    override fun create(data: UpdateableCreator.Data): CloseableUpdateable {
        val driverStation = MutableFrcDriverStation()
        data.uiStage.addActor(PracticeSimulation.createSideTable(driverStation, data.uiSkin))
        return CloseableUpdateableMultiplexer(listOf(
                CloseableUpdateable.fromUpdateable(KeyPressStopUpdateable {
                    driverStation.mode = FrcMode.DISABLED
                }),
                robotCreator.create(RobotCreator.Data(driverStation), data),
                CloseableUpdateable.fromUpdateable(ScoreboardUpdateable(data.uiStage, driverStation)),
                CloseableUpdateable.fromUpdateable(CoordinateUpdateable(data.uiStage, data.contentStage))
        ))
    }

}
