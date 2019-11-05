package com.first1444.sim.gdx.init

import com.first1444.sim.api.frc.FrcMode
import com.first1444.sim.api.frc.sim.MutableFrcDriverStation
import com.first1444.sim.gdx.KeyPressStopUpdateable
import com.first1444.sim.gdx.Updateable
import com.first1444.sim.gdx.UpdateableMultiplexer
import com.first1444.sim.gdx.ui.PracticeSimulation
import com.first1444.sim.gdx.ui.scoreboard.ScoreboardUpdateable

class PracticeUpdateableCreator(
        private val robotCreator: RobotCreator
) : UpdateableCreator {
    override fun create(data: UpdateableCreator.Data): Updateable {
        val driverStation = MutableFrcDriverStation()
        data.uiStage.addActor(PracticeSimulation.createSideTable(driverStation, data.uiSkin))
        return UpdateableMultiplexer(listOf(
                KeyPressStopUpdateable {
                    driverStation.mode = FrcMode.DISABLED
                },
                robotCreator.create(RobotCreator.Data(driverStation), data),
                ScoreboardUpdateable(data.uiStage, driverStation)
        ))
    }

}
