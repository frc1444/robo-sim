package com.first1444.sim.api.frc.implementations.infiniterecharge

import com.first1444.sim.api.Rotation2
import com.first1444.sim.api.Transform2
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


    /** The alliance loading bay on the alliance's side of the field */
    @JvmField val ALLIANCE_LOADING_BAY = VisionTarget2020(Transform2(-WIDTH / 2.0 + inchesToMeters(94.66 + 48.0 / 2 + 6.0 * 12 + 60.0 / 2), -LENGTH / 2.0, Rotation2.DEG_270), VisionIdentifier2020(VisionType2020.LOADING_BAY, isEnemyOwner = false))
    @JvmField val ENEMY_LOADING_BAY = VisionTarget2020(-ALLIANCE_LOADING_BAY.transform, VisionIdentifier2020(VisionType2020.LOADING_BAY, isEnemyOwner = true))

    @JvmField val ALLIANCE_POWER_PORT = VisionTarget2020(Transform2(WIDTH / 2 - inchesToMeters(94.66), LENGTH / 2, Rotation2.DEG_90), VisionIdentifier2020(VisionType2020.POWER_PORT, isEnemyOwner = false))
    @JvmField val ENEMY_POWER_PORT = VisionTarget2020(-ALLIANCE_POWER_PORT.transform, VisionIdentifier2020(VisionType2020.POWER_PORT, isEnemyOwner = true))

    @JvmField val ALL_VISION_TARGETS = listOf(ALLIANCE_LOADING_BAY, ENEMY_LOADING_BAY, ALLIANCE_POWER_PORT, ENEMY_POWER_PORT)
}
