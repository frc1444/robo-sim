package com.first1444.sim.gdx.init

import com.first1444.sim.api.frc.FrcDriverStation
import com.first1444.sim.gdx.CloseableUpdateable
import com.first1444.sim.gdx.Updateable

interface RobotCreator {
    fun create(data: Data, updateableData: UpdateableCreator.Data): CloseableUpdateable

    companion object {
        @JvmSynthetic
        inline operator fun invoke(crossinline lambda: (data: Data, updateableData: UpdateableCreator.Data) -> CloseableUpdateable): RobotCreator {
            return object : RobotCreator {
                override fun create(data: Data, updateableData: UpdateableCreator.Data): CloseableUpdateable {
                    return lambda(data, updateableData)
                }
            }
        }
    }

    class Data (
            val driverStation: FrcDriverStation
    )
}
