package com.first1444.sim.gdx.init

import com.first1444.sim.gdx.*
import com.first1444.sim.gdx.implementations.deepspace2019.CargoEntity
import com.first1444.sim.gdx.implementations.deepspace2019.FieldSetup2019

object Field2019Creator : UpdateableCreator {
    override fun create(data: UpdateableCreator.Data): Updateable {
        val updateableList = mutableListOf<Updateable>()
        for(i in 1..24) {
            val cargo = CargoEntity(data.contentStage, data.worldManager.world)
            cargo.position = gdxVector(3.0f, 3.0f)
            updateableList.add(cargo)
        }
        FieldSetup2019.createField(data.worldManager.world)
        FieldSetup2019.createVisionTargets(data.worldManager.world)
        return UpdateableMultiplexer(updateableList)
    }

}
