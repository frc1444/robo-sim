package com.first1444.sim.gdx.init

import com.first1444.sim.api.frc.FrcDriverStation
import com.first1444.sim.gdx.Updateable

interface RobotCreator {
    fun create(data: Data, updateableData: UpdateableCreator.Data): Updateable

    companion object {
        operator fun invoke(lambda: (data: Data, updateableData: UpdateableCreator.Data) -> Updateable): RobotCreator {
            return object : RobotCreator {
                override fun create(data: Data, updateableData: UpdateableCreator.Data): Updateable {
                    return lambda(data, updateableData)
                }
            }
        }
    }

    class Data (
            val driverStation: FrcDriverStation
    )
}
