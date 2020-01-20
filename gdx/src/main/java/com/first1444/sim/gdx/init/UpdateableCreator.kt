package com.first1444.sim.gdx.init

import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.first1444.sim.api.Clock
import com.first1444.sim.gdx.CloseableUpdateable
import com.first1444.sim.gdx.WorldManager

interface UpdateableCreator {
    fun create(data: Data): CloseableUpdateable

    companion object {
        inline operator fun invoke(crossinline lambda: (data: Data) -> CloseableUpdateable): UpdateableCreator = object : UpdateableCreator {
            override fun create(data: Data): CloseableUpdateable {
                return lambda(data)
            }
        }
    }

    class Data(
            val uiSkin: Skin,
            val clock: Clock,
            val uiStage: Stage,
            val contentStage: Stage,
            val worldManager: WorldManager
    )
}
