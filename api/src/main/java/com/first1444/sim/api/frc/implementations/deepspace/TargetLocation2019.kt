package com.first1444.sim.api.frc.implementations.deepspace

enum class TargetLocation2019(
        val bayType: BayType2019
) {
    LOADING_STATION(BayType2019.LOADING_STATION),
    CARGO_SHIP_FRONT(BayType2019.CARGO_SHIP),
    CARGO_SHIP_1(BayType2019.CARGO_SHIP),
    CARGO_SHIP_2(BayType2019.CARGO_SHIP),
    CARGO_SHIP_3(BayType2019.CARGO_SHIP),
    ROCKET_NEAR(BayType2019.ROCKET_HATCH),
    ROCKET_FAR(BayType2019.ROCKET_HATCH),
    ROCKET_FRONT(BayType2019.ROCKET_CARGO);
}
