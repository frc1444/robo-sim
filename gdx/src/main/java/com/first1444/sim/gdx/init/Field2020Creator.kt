package com.first1444.sim.gdx.init

import com.first1444.sim.api.frc.implementations.infiniterecharge.Field2020
import com.first1444.sim.gdx.Updateable
import com.first1444.sim.gdx.UpdateableMultiplexer
import com.first1444.sim.gdx.implementations.infiniterecharge2020.FieldSetup2020
import com.first1444.sim.gdx.implementations.infiniterecharge2020.PowerCellEntity
import com.first1444.sim.gdx.implementations.infiniterecharge2020.PowerCellUserData

object Field2020Creator : UpdateableCreator {
    override fun create(data: UpdateableCreator.Data): Updateable {
        for(vector in Field2020.ALL_POWER_CELL_STARTING){
            val powerCell = PowerCellEntity(data.contentStage, data.worldManager.world)
            powerCell.simVector = vector
            data.worldManager.add(powerCell)
            powerCell.body.userData = PowerCellUserData(powerCell, vector)
        }
        FieldSetup2020.createField(data.worldManager.world)
        return Updateable.UPDATE_NOTHING
    }
}
