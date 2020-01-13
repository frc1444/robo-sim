package com.first1444.sim.gdx.init

import com.first1444.sim.api.frc.FrcDriverStation
import com.first1444.sim.gdx.CloseableUpdateable
import com.first1444.sim.gdx.CloseableUpdateableMultiplexer
import com.first1444.sim.gdx.ui.CoordinateUpdateable
import com.first1444.sim.gdx.ui.scoreboard.ScoreboardUpdateable

class SupplementaryUpdateableCreator(
        private val robotCreator: RobotCreator,
        private val driverStation: FrcDriverStation
) : UpdateableCreator {
    override fun create(data: UpdateableCreator.Data): CloseableUpdateable {
        return CloseableUpdateableMultiplexer(listOf(
                robotCreator.create(RobotCreator.Data(driverStation), data),
                CloseableUpdateable.fromUpdateable(ScoreboardUpdateable(data.uiStage, driverStation)),
                CloseableUpdateable.fromUpdateable(CoordinateUpdateable(data.uiStage, data.contentStage))
        ))
    }

}
