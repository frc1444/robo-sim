package com.first1444.sim.gdx.init

import com.first1444.sim.api.frc.FrcDriverStation
import com.first1444.sim.gdx.Updateable
import com.first1444.sim.gdx.UpdateableMultiplexer
import com.first1444.sim.gdx.ui.CoordinateUpdateable
import com.first1444.sim.gdx.ui.scoreboard.ScoreboardUpdateable

class SupplementaryUpdateableCreator(
        private val robotCreator: RobotCreator,
        private val driverStation: FrcDriverStation
) : UpdateableCreator {
    override fun create(data: UpdateableCreator.Data): Updateable {
        return UpdateableMultiplexer(listOf(
                robotCreator.create(RobotCreator.Data(driverStation), data),
                ScoreboardUpdateable(data.uiStage, driverStation),
                CoordinateUpdateable(data.uiStage, data.contentStage)
        ))
    }

}
