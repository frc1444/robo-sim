package com.first1444.sim.gdx.init

import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.first1444.sim.api.frc.DriverStationLocation
import com.first1444.sim.api.frc.MatchInfo
import com.first1444.sim.api.frc.MatchType
import com.first1444.sim.api.frc.sim.FmsFrcDriverStation
import com.first1444.sim.api.frc.sim.MatchFmsSimulator
import com.first1444.sim.gdx.CloseableUpdateable
import com.first1444.sim.gdx.CloseableUpdateableMultiplexer
import com.first1444.sim.gdx.KeyPressStopUpdateable
import com.first1444.sim.gdx.clickDownListener
import com.first1444.sim.gdx.ui.DriverStationConfig
import com.first1444.sim.gdx.ui.scoreboard.ScoreboardUpdateable

class RealUpdateableCreator(
        private val uiSkin: Skin,
        private val config: RealConfig,
        private val robotCreator: RobotCreator
) : UpdateableCreator {
    override fun create(data: UpdateableCreator.Data): CloseableUpdateable {
        val fms = MatchFmsSimulator(data.clock, MatchInfo("", MatchType.NONE, 0, 0))
        val gameSpecificMessageHolder = arrayOf("")
        val driverStation = FmsFrcDriverStation(fms, config.alliance, config.driverStationLocation) { gameSpecificMessageHolder[0] }
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
        sideTable.row()
        DriverStationConfig.populateGameSpecificMessage(sideTable, uiSkin) { gameSpecificMessageHolder[0] = it }
        return CloseableUpdateableMultiplexer(listOf(
                CloseableUpdateable.fromUpdateable(KeyPressStopUpdateable {
                    fms.stop()
                }),
                robotCreator.create(RobotCreator.Data(driverStation), data),
                CloseableUpdateable.fromUpdateable(ScoreboardUpdateable(data.uiStage, fms))
        ))
    }

}
