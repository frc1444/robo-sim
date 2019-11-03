package com.first1444.sim.gdx.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.first1444.sim.api.frc.Alliance
import com.first1444.sim.api.frc.DriverStationLocation
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
                driverStation.mode = FrcMode.TELEOP
            })
        })
        sideTable.row()
        sideTable.add(TextButton("Autonomous", uiSkin).apply {
            addListener(clickDownListener {
                driverStation.mode = FrcMode.AUTONOMOUS
            })
        })
        sideTable.row()
        sideTable.add(TextButton("Test", uiSkin).apply {
            addListener(clickDownListener {
                driverStation.mode = FrcMode.TEST
            })
        })
        sideTable.row()
        sideTable.add(Table().apply {
            add(TextButton("1", uiSkin).apply {
                addListener(clickDownListener {
                    driverStation.driverStationLocation = DriverStationLocation.LEFT
                })
            })
            add(TextButton("2", uiSkin).apply {
                addListener(clickDownListener {
                    driverStation.driverStationLocation = DriverStationLocation.MIDDLE
                })
            })
            add(TextButton("3", uiSkin).apply {
                addListener(clickDownListener {
                    driverStation.driverStationLocation = DriverStationLocation.RIGHT
                })
            })
        })
        sideTable.row()
        sideTable.add(Table().apply {
            add(TextButton("Red", uiSkin).apply {
                addListener(clickDownListener {
                    driverStation.alliance = Alliance.RED
                })
            })
            add(TextButton("Blue", uiSkin).apply {
                addListener(clickDownListener {
                    driverStation.alliance = Alliance.BLUE
                })
            })
        })
        sideTable.row()
        val textLabel = TextField("", uiSkin)
        sideTable.add(textLabel)
        sideTable.row()
        sideTable.add(TextButton("Change GSM", uiSkin).apply {
            addListener(clickDownListener {
                driverStation.gameSpecificMessage = textLabel.text
            })
        })
        return sideTable
    }
}
