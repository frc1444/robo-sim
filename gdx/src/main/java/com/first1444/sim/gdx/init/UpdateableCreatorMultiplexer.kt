package com.first1444.sim.gdx.init

import com.first1444.sim.gdx.CloseableUpdateable
import com.first1444.sim.gdx.CloseableUpdateableMultiplexer

class UpdateableCreatorMultiplexer(
        private val updateableCreatorList: List<UpdateableCreator>
) : UpdateableCreator {
    override fun create(data: UpdateableCreator.Data): CloseableUpdateable {
        val list = ArrayList<CloseableUpdateable>()
        for(creator in updateableCreatorList){
            list.add(creator.create(data))
        }
        return CloseableUpdateableMultiplexer(list)
    }
}
