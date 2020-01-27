package com.first1444.sim.gdx.init

import com.first1444.sim.gdx.Updateable
import com.first1444.sim.gdx.UpdateableMultiplexer

class UpdateableCreatorMultiplexer(
        private val updateableCreatorList: List<UpdateableCreator>
) : UpdateableCreator {
    override fun create(data: UpdateableCreator.Data): Updateable {
        val list = ArrayList<Updateable>()
        for(creator in updateableCreatorList){
            list.add(creator.create(data))
        }
        return UpdateableMultiplexer(list)
    }
}
