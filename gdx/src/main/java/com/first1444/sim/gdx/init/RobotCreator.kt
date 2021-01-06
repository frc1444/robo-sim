package com.first1444.sim.gdx.init

import com.first1444.sim.api.frc.FrcDriverStation
import com.first1444.sim.gdx.Updateable

fun interface RobotCreator {
    fun create(data: Data, updateableData: UpdateableCreator.Data): Updateable

    class Data (
            val driverStation: FrcDriverStation
    )
}
