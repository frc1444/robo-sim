package com.first1444.sim.gdx.ui

import com.badlogic.gdx.scenes.scene2d.ui.*
import com.first1444.sim.api.frc.Alliance
import com.first1444.sim.api.frc.DriverStationLocation
import com.first1444.sim.api.frc.sim.MutableFrcDriverStation
import com.first1444.sim.gdx.changeEventListener

object DriverStationConfig {
    @JvmStatic
    fun populateTable(table: Table, driverStation: MutableFrcDriverStation, uiSkin: Skin){
        // block for driver station location
        run {
            val location = driverStation.driverStationLocation
            val buttonGroup = ButtonGroup(
                    CheckBox("1", uiSkin).apply {
                        if (location == DriverStationLocation.LEFT) {
                            isChecked = true
                        }
                    },
                    CheckBox("2", uiSkin).apply {
                        if (location == DriverStationLocation.MIDDLE) {
                            isChecked = true
                        }
                    },
                    CheckBox("3", uiSkin).apply {
                        if (location == DriverStationLocation.RIGHT) {
                            isChecked = true
                        }
                    }
            )
            buttonGroup.setMaxCheckCount(1)
            buttonGroup.setMinCheckCount(1)
            table.add(Table().apply {
                for (button in buttonGroup.buttons) {
                    add(button)
                }
                addListener(changeEventListener {
                    val checkedIndex = buttonGroup.checkedIndex
                    if (checkedIndex != -1) {
                        driverStation.driverStationLocationValue = checkedIndex + 1
                    }
                })
            })
        }
        table.row()
        run {
            val alliance = driverStation.alliance
            val buttonGroup = ButtonGroup(
                    CheckBox("Red", uiSkin).apply {
                        if(alliance == Alliance.RED) {
                            isChecked = true
                        }
                    },
                    CheckBox("Blue", uiSkin).apply {
                        if(alliance == Alliance.BLUE) {
                            isChecked = true
                        }
                    }
            )
            buttonGroup.setMaxCheckCount(1)
            buttonGroup.setMinCheckCount(1)
            table.add(Table().apply {
                for(button in buttonGroup.buttons){
                    add(button)
                }
                addListener(changeEventListener {
                    val checkedIndex = buttonGroup.checkedIndex
                    if (checkedIndex != -1) {
                        driverStation.alliance = when(checkedIndex){
                            0 -> Alliance.RED
                            1 -> Alliance.BLUE
                            else -> error("Unknown index: $checkedIndex")
                        }
                    }
                })
            })
        }
        table.row()
    }
    @JvmStatic
    fun populateGameSpecificMessage(table: Table, driverStation: MutableFrcDriverStation, uiSkin: Skin) = populateGameSpecificMessage(table, uiSkin) { driverStation.gameSpecificMessage = it }
    @JvmStatic
    fun populateGameSpecificMessage(table: Table, uiSkin: Skin, gameSpecificMessageSetter: (String) -> Unit) {
        table.row()
        val textLabel = TextField("", uiSkin)
        textLabel.setTextFieldListener { _, _ ->
            gameSpecificMessageSetter(textLabel.text)
        }
        table.add(textLabel)
    }
}
