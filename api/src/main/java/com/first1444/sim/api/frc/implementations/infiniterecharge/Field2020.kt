package com.first1444.sim.api.frc.implementations.infiniterecharge

import com.first1444.sim.api.Vector2
import com.first1444.sim.api.inchesToMeters

object Field2020 {
    @JvmField
    val WIDTH: Double = inchesToMeters(26 * 12 + 11.25)
    @JvmField
    val LENGTH: Double = inchesToMeters(52 * 12 + 5.25)

    @JvmField
    val ALLIANCE_POWER_CELL_STARTING: List<Vector2> = listOf(
            Vector2(WIDTH / 2 - inchesToMeters(27.75), LENGTH / 2 - inchesToMeters(130 + 122.63)),
            Vector2(WIDTH / 2 - inchesToMeters(27.75), LENGTH / 2 - inchesToMeters(130 + 122.63 + 36.0)),
            Vector2(WIDTH / 2 - inchesToMeters(27.75), LENGTH / 2 - inchesToMeters(130 + 194.63))
    )
    @JvmField val ENEMY_POWER_CELL_STARTING = ALLIANCE_POWER_CELL_STARTING.map { -it }
    @JvmField val ALL_POWER_CELL_STARTING = ALLIANCE_POWER_CELL_STARTING + ENEMY_POWER_CELL_STARTING
}
