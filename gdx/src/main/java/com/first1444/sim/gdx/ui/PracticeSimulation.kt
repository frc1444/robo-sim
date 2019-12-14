package com.first1444.sim.gdx.ui

import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.first1444.sim.api.frc.FrcMode
import com.first1444.sim.api.frc.sim.MutableFrcDriverStation
import com.first1444.sim.gdx.clickDownListener

object PracticeSimulation {
    @JvmStatic
    fun createSideTable(driverStation: MutableFrcDriverStation, uiSkin: Skin): Table {
        val sideTable = Table()
        sideTable.setFillParent(true)
        sideTable.left()
        sideTable.add(TextButton("TeleOp", uiSkin).apply {
            addListener(clickDownListener {
                trySetMode(driverStation, FrcMode.TELEOP)
            })
        })
        sideTable.row()
        sideTable.add(TextButton("Autonomous", uiSkin).apply {
            addListener(clickDownListener {
                trySetMode(driverStation, FrcMode.AUTONOMOUS)
            })
        })
        sideTable.row()
        sideTable.add(TextButton("Test", uiSkin).apply {
            addListener(clickDownListener {
                trySetMode(driverStation, FrcMode.TEST)
            })
        })
        sideTable.row()
        DriverStationConfig.populateTable(sideTable, driverStation, uiSkin)

        return sideTable
    }
    private fun trySetMode(driverStation: MutableFrcDriverStation, mode: FrcMode) {
        val currentMode = driverStation.mode
        if(currentMode == mode){
            return
        }
        if(currentMode == FrcMode.DISABLED){
            driverStation.mode = mode
        } else {
            driverStation.mode = FrcMode.DISABLED
        }
    }
}
