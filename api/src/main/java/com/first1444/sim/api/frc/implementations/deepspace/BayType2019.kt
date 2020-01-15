package com.first1444.sim.api.frc.implementations.deepspace

enum class BayType2019(
        val visionType: VisionType2019
) {
    LOADING_STATION(VisionType2019.NORMAL),
    CARGO_SHIP(VisionType2019.NORMAL),
    /**
     * The bays on the side of the rocket
     */
    ROCKET_HATCH(VisionType2019.NORMAL),
    /**
     * The bays on the front of the rocket
     */
    ROCKET_CARGO(VisionType2019.FRONT_ROCKET);
}
