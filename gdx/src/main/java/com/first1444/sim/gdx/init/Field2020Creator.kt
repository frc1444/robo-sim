package com.first1444.sim.gdx.init

import com.first1444.sim.api.frc.implementations.infiniterecharge.Field2020
import com.first1444.sim.gdx.CloseableUpdateable
import com.first1444.sim.gdx.Updateable
import com.first1444.sim.gdx.UpdateableMultiplexer
import com.first1444.sim.gdx.implementations.infiniterecharge.FieldSetup2020
import com.first1444.sim.gdx.implementations.infiniterecharge.PowerCellEntity

object Field2020Creator : UpdateableCreator {
    override fun create(data: UpdateableCreator.Data): CloseableUpdateable {
        val updateableList = mutableListOf<Updateable>()
        for(vector in Field2020.ALL_POWER_CELL_STARTING){
            val powerCell = PowerCellEntity(data.contentStage, data.worldManager.world)
            powerCell.simVector = vector
            updateableList.add(powerCell)
        }
        FieldSetup2020.createField(data.worldManager.world)
        return CloseableUpdateable.fromUpdateable(UpdateableMultiplexer(updateableList))
    }

}
