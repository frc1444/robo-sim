package com.first1444.sim.api.frc.implementations.deepspace

import com.first1444.sim.api.inchesToMeters
import com.first1444.sim.api.Transform.Companion.transformDegrees
import com.first1444.sim.api.Vector2

object Field2019 {
    @JvmField
    val WIDTH: Double = inchesToMeters(27 * 12.0)
    @JvmField
    val LENGTH: Double = inchesToMeters(54 * 12.0)

    @JvmField val HAB_LEVEL_1_LENGTH = inchesToMeters(36.0)
    @JvmField val HAB_LIP_MEASUREMENT = inchesToMeters(11.5)
    @JvmField val HAB_CARGO_BAY_EXTEND = inchesToMeters(26.0)

    @JvmField val CARGO_SHIP_TOTAL_LENGTH = inchesToMeters(95.88)
    @JvmField val CARGO_SHIP_BUMPER_LENGTH = CARGO_SHIP_TOTAL_LENGTH - inchesToMeters(7.38)
    @JvmField val CARGO_SHIP_BUMPER_WIDTH = inchesToMeters(45.5)
    @JvmField val CARGO_SHIP_TOTAL_WIDTH = CARGO_SHIP_BUMPER_WIDTH + 2 * inchesToMeters(5.23f)

    @JvmField val LOADING_STATION_DISTANCE_FROM_WALL = inchesToMeters(27.44 - 1) // The width of a ground vision strip is 2 inches. Half of that is 1 inch.

    @JvmField val CARGO_SHIP_HALF_GAP = inchesToMeters(9.0)
    /** The distance between both cargo ships*/
    @JvmField val CARGO_SHIP_GAP = CARGO_SHIP_HALF_GAP * 2
    @JvmField val CARGO_SHIP_BAY_SPACING = inchesToMeters(21.75)

    @JvmField val LOADING_LEFT  = VisionTarget(transformDegrees(-WIDTH / 2 + LOADING_STATION_DISTANCE_FROM_WALL, -LENGTH / 2, -90.0), VisionType.LOADING_STATION)
    @JvmField val LOADING_RIGHT = VisionTarget(transformDegrees(WIDTH / 2 - LOADING_STATION_DISTANCE_FROM_WALL, -LENGTH / 2, -90.0), VisionType.LOADING_STATION)

    @JvmField val CARGO_SHIP_CENTER_LEFT = VisionTarget(transformDegrees(-CARGO_SHIP_BAY_SPACING / 2, -(CARGO_SHIP_HALF_GAP + CARGO_SHIP_TOTAL_LENGTH), 90.0), VisionType.CARGO_SHIP)
    @JvmField val CARGO_SHIP_CENTER_RIGHT = VisionTarget(transformDegrees(CARGO_SHIP_BAY_SPACING / 2, -(CARGO_SHIP_HALF_GAP + CARGO_SHIP_TOTAL_LENGTH), 90.0), VisionType.CARGO_SHIP)

    @JvmField val CARGO_SHIP_LEFT_3 = VisionTarget(transformDegrees(-CARGO_SHIP_TOTAL_WIDTH / 2, -(CARGO_SHIP_HALF_GAP + CARGO_SHIP_BAY_SPACING / 2), 0.0), VisionType.CARGO_SHIP)
    @JvmField val CARGO_SHIP_LEFT_2 = VisionTarget(transformDegrees(-CARGO_SHIP_TOTAL_WIDTH / 2, -(CARGO_SHIP_HALF_GAP + CARGO_SHIP_BAY_SPACING * 3 / 2), 0.0), VisionType.CARGO_SHIP)
    @JvmField val CARGO_SHIP_LEFT_1 = VisionTarget(transformDegrees(-CARGO_SHIP_TOTAL_WIDTH / 2, -(CARGO_SHIP_HALF_GAP + CARGO_SHIP_BAY_SPACING * 5 / 2), 0.0), VisionType.CARGO_SHIP)

    @JvmField val CARGO_SHIP_RIGHT_3 = VisionTarget(transformDegrees(CARGO_SHIP_TOTAL_WIDTH / 2, -(CARGO_SHIP_HALF_GAP + CARGO_SHIP_BAY_SPACING / 2), 180.0), VisionType.CARGO_SHIP)
    @JvmField val CARGO_SHIP_RIGHT_2 = VisionTarget(transformDegrees(CARGO_SHIP_TOTAL_WIDTH / 2, -(CARGO_SHIP_HALF_GAP + CARGO_SHIP_BAY_SPACING * 3 / 2), 180.0), VisionType.CARGO_SHIP)
    @JvmField val CARGO_SHIP_RIGHT_1 = VisionTarget(transformDegrees(CARGO_SHIP_TOTAL_WIDTH / 2, -(CARGO_SHIP_HALF_GAP + CARGO_SHIP_BAY_SPACING * 5 / 2), 180.0), VisionType.CARGO_SHIP)

    @JvmField val ROCKET_LEFT_POSITION = Vector2(-WIDTH / 2, inchesToMeters(-96.0))
    @JvmField val ROCKET_RIGHT_POSITION = ROCKET_LEFT_POSITION.times(-1.0, 1.0)
    private val ROCKET_HATCH_OFFSET = Vector2(inchesToMeters((8.83 + 26.36) / 2), inchesToMeters(56.0 / 2 - (9.5 + 19) / 2))

    @JvmField val ROCKET_SHIP_LEFT_HATCH_FAR = VisionTarget(transformDegrees(ROCKET_LEFT_POSITION + ROCKET_HATCH_OFFSET * Vector2.ONE, 61.23 - 180), VisionType.ROCKET_HATCH)
    @JvmField val ROCKET_SHIP_LEFT_HATCH_NEAR = VisionTarget(transformDegrees(ROCKET_LEFT_POSITION + ROCKET_HATCH_OFFSET.times(1.0, -1.0), 180 - 61.23), VisionType.ROCKET_HATCH)
    @JvmField val ROCKET_SHIP_LEFT_CARGO = VisionTarget(transformDegrees(ROCKET_LEFT_POSITION.plus(inchesToMeters(-26.36), 0.0), 180.0), VisionType.ROCKET_CARGO)

    @JvmField val ROCKET_SHIP_RIGHT_HATCH_FAR = VisionTarget(transformDegrees(ROCKET_RIGHT_POSITION + ROCKET_HATCH_OFFSET.times(-1.0, 1.0), -61.23), VisionType.ROCKET_HATCH)
    @JvmField val ROCKET_SHIP_RIGHT_HATCH_NEAR = VisionTarget(transformDegrees(ROCKET_RIGHT_POSITION + ROCKET_HATCH_OFFSET.times(-1.0, -1.0), 61.23), VisionType.ROCKET_HATCH)
    @JvmField val ROCKET_SHIP_RIGHT_CARGO = VisionTarget(transformDegrees(ROCKET_RIGHT_POSITION.plus(inchesToMeters(26.36), 0.0), 0.0), VisionType.ROCKET_CARGO)


    @JvmField val ALLIANCE_VISION_TARGETS = listOf(
            LOADING_LEFT, LOADING_RIGHT,
            CARGO_SHIP_CENTER_LEFT, CARGO_SHIP_CENTER_RIGHT,
            CARGO_SHIP_LEFT_1, CARGO_SHIP_LEFT_2, CARGO_SHIP_LEFT_3,
            CARGO_SHIP_RIGHT_1, CARGO_SHIP_RIGHT_2, CARGO_SHIP_RIGHT_3,
            ROCKET_SHIP_LEFT_HATCH_FAR, ROCKET_SHIP_LEFT_HATCH_NEAR,
            ROCKET_SHIP_RIGHT_HATCH_FAR, ROCKET_SHIP_RIGHT_HATCH_NEAR,
            ROCKET_SHIP_LEFT_CARGO, ROCKET_SHIP_RIGHT_CARGO
    )
    @JvmField val ENEMY_VISION_TARGETS = ALLIANCE_VISION_TARGETS.map { VisionTarget(-it.transform, it.visionType) }
    @JvmField val ALL_VISION_TARGETS = ALLIANCE_VISION_TARGETS + ENEMY_VISION_TARGETS

}
