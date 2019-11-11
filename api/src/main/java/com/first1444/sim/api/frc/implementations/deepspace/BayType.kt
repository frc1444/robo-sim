package com.first1444.sim.api.frc.implementations.deepspace

enum class BayType(
        val visionType: VisionType
) {
    LOADING_STATION(VisionType.NORMAL),
    CARGO_SHIP(VisionType.NORMAL),
    /**
     * The bays on the side of the rocket
     */
    ROCKET_HATCH(VisionType.NORMAL),
    /**
     * The bays on the front of the rocket
     */
    ROCKET_CARGO(VisionType.FRONT_ROCKET);
}