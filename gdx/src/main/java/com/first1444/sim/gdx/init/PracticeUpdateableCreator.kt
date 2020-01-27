package com.first1444.sim.gdx.init

import com.first1444.sim.api.frc.FrcMode
import com.first1444.sim.api.frc.sim.MutableFrcDriverStation
import com.first1444.sim.gdx.*
import com.first1444.sim.gdx.ui.CoordinateUpdateable
import com.first1444.sim.gdx.ui.PracticeSimulation
import com.first1444.sim.gdx.ui.scoreboard.ScoreboardUpdateable

/**
 * An [UpdateableCreator] that is used in "practice" mode
 */
class PracticeUpdateableCreator(
        private val robotCreator: RobotCreator
) : UpdateableCreator {
    override fun create(data: UpdateableCreator.Data): Updateable {
        val driverStation = MutableFrcDriverStation()
        data.uiStage.addActor(PracticeSimulation.createSideTable(driverStation, data.uiSkin)) // adds Teleop, Autonomous, Test buttons
        return UpdateableMultiplexer(listOf(
                KeyPressStopUpdateable { // disable when pressing enter
                    driverStation.mode = FrcMode.DISABLED
                },
                robotCreator.create(RobotCreator.Data(driverStation), data),
                ScoreboardUpdateable(data.uiStage, driverStation),
                CoordinateUpdateable(data.uiStage, data.contentStage)
        ))
    }

}
