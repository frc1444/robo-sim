package com.first1444.sim.gdx.init

import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.first1444.sim.api.frc.MatchInfo
import com.first1444.sim.api.frc.sim.FmsFrcDriverStation
import com.first1444.sim.api.frc.sim.MatchFmsSimulator
import com.first1444.sim.gdx.*
import com.first1444.sim.gdx.ui.scoreboard.ScoreboardUpdateable

class RealUpdateableCreator(
        private val uiSkin: Skin,
        private val config: RealConfig,
        private val robotCreator: RobotCreator
) : UpdateableCreator {
    override fun create(data: UpdateableCreator.Data): CloseableUpdateable {
        val fms = MatchFmsSimulator(data.clock, MatchInfo("", null, 0, 0))
        val driverStation = FmsFrcDriverStation(fms, config.alliance, config.driverStationLocation, config.gameSpecificMessage)
        val sideTable = Table()
        data.uiStage.addActor(sideTable)
        sideTable.setFillParent(true)
        sideTable.left()
        sideTable.add(TextButton("Start", uiSkin).apply {
            addListener(clickDownListener {
                fms.start(null)
            })
        })
        sideTable.row()
        sideTable.add(TextButton("Stop", uiSkin).apply {
            addListener(clickDownListener {
                fms.stop()
            })
        })
        return CloseableUpdateableMultiplexer(listOf(
                CloseableUpdateable.fromUpdateable(KeyPressStopUpdateable { // TODO maybe we only want to disable a single robot instead of the entire match
                    fms.stop()
                }),
                robotCreator.create(RobotCreator.Data(driverStation), data),
                CloseableUpdateable.fromUpdateable(ScoreboardUpdateable(data.uiStage, fms))
        ))
    }

}
