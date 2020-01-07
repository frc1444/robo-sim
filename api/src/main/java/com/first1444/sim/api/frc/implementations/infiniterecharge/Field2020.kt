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
            Vector2(WIDTH / 2 - inchesToMeters(27.75), LENGTH / 2 - inchesToMeters(120 + 122.63)),
            Vector2(WIDTH / 2 - inchesToMeters(27.75), LENGTH / 2 - inchesToMeters(120 + 122.63 + 36.0)),
            Vector2(WIDTH / 2 - inchesToMeters(27.75), LENGTH / 2 - inchesToMeters(120 + 194.63)),

            Vector2(-WIDTH / 2 + inchesToMeters(94.66 + 191.43), -LENGTH / 2 + inchesToMeters(253.49)),
            Vector2(-WIDTH / 2 + inchesToMeters(94.66 + 191.43 + 20.0 /* TODO Change this 20!*/), -LENGTH / 2 + inchesToMeters(253.49)),

            Vector2(WIDTH / 2 - inchesToMeters(94.66 + 81.14), LENGTH / 2 - inchesToMeters(120 + 120.51)), // center left
            Vector2(WIDTH / 2 - inchesToMeters(94.66 + 65.84), LENGTH / 2 - inchesToMeters(120 + 114.17)), // center middle
            Vector2(WIDTH / 2 - inchesToMeters(94.66 + 50.54), LENGTH / 2 - inchesToMeters(120 + 107.83)), // center right

            Vector2(WIDTH / 2 - inchesToMeters(94.66 + 26.13), LENGTH / 2 - inchesToMeters(120 + 114.94)), // right left
            Vector2(WIDTH / 2 - inchesToMeters(94.66 + 19.79), LENGTH / 2 - inchesToMeters(120 + 130.25)) // right right
    )
    @JvmField val ENEMY_POWER_CELL_STARTING = ALLIANCE_POWER_CELL_STARTING.map { -it }
    @JvmField val ALL_POWER_CELL_STARTING = ALLIANCE_POWER_CELL_STARTING + ENEMY_POWER_CELL_STARTING
}
