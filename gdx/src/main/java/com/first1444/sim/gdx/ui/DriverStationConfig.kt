package com.first1444.sim.gdx.ui

import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.first1444.sim.api.frc.Alliance
import com.first1444.sim.api.frc.DriverStationLocation
import com.first1444.sim.api.frc.sim.MutableFrcDriverStation
import com.first1444.sim.gdx.clickDownListener

object DriverStationConfig {
    @JvmStatic
    fun populateTable(table: Table, driverStation: MutableFrcDriverStation, uiSkin: Skin){

        table.add(Table().apply {
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
        table.row()
        table.add(Table().apply {
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
        table.row()
        val textLabel = TextField("", uiSkin)
        textLabel.setTextFieldListener { _, _ ->
            driverStation.gameSpecificMessage = textLabel.text
        }
        table.add(textLabel)
        table.row()
    }
}
