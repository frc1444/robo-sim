package com.first1444.sim.gdx.init

import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.first1444.sim.api.frc.MatchInfo
import com.first1444.sim.api.frc.MatchType
import com.first1444.sim.api.frc.sim.FmsFrcDriverStation
import com.first1444.sim.api.frc.sim.MatchFmsSimulator
import com.first1444.sim.gdx.Updateable
import com.first1444.sim.gdx.UpdateableMultiplexer
import com.first1444.sim.gdx.KeyPressStopUpdateable
import com.first1444.sim.gdx.clickDownListener
import com.first1444.sim.gdx.ui.DriverStationConfig
import com.first1444.sim.gdx.ui.scoreboard.ScoreboardUpdateable

class RealUpdateableCreator(
        private val config: RealConfig,
        private val robotCreator: RobotCreator
) : UpdateableCreator {
    override fun create(data: UpdateableCreator.Data): Updateable {
        val fms = MatchFmsSimulator(data.clock, MatchInfo("", MatchType.NONE, 0, 0))
        val gameSpecificMessageHolder = arrayOf("")
        val driverStation = FmsFrcDriverStation(fms, config.alliance, config.driverStationLocation) { gameSpecificMessageHolder[0] }
        val sideTable = Table()
        data.uiStage.addActor(sideTable)
        sideTable.setFillParent(true)
        sideTable.left()
        sideTable.add(TextButton("Start", data.uiSkin).apply {
            addListener(clickDownListener {
                fms.start(null)
            })
        })
        sideTable.row()
        sideTable.add(TextButton("Stop", data.uiSkin).apply {
            addListener(clickDownListener {
                fms.stop()
            })
        })
        sideTable.row()
        DriverStationConfig.populateGameSpecificMessage(sideTable, data.uiSkin) { gameSpecificMessageHolder[0] = it }
        return UpdateableMultiplexer(listOf(
                KeyPressStopUpdateable {
                    fms.stop()
                },
                robotCreator.create(RobotCreator.Data(driverStation), data),
                ScoreboardUpdateable(data.uiStage, fms)
        ))
    }

}
