package com.first1444.sim.gdx.init

import com.first1444.sim.gdx.Updateable
import com.first1444.sim.gdx.UpdateableMultiplexer
import com.first1444.sim.gdx.gdxVector
import com.first1444.sim.gdx.implementations.deepspace2019.CargoEntity
import com.first1444.sim.gdx.implementations.deepspace2019.Field2019

object Field2019Creator : UpdateableCreator {
    override fun create(data: UpdateableCreator.Data): Updateable {
        val updateableList = mutableListOf<Updateable>()
        for(i in 1..24) {
            val cargo = CargoEntity(data.contentStage, data.worldManager.world)
            cargo.position = gdxVector(3.0f, 3.0f)
            updateableList.add(cargo)
        }
        Field2019.createField(data.worldManager.world)
        return UpdateableMultiplexer(updateableList)
    }

}
