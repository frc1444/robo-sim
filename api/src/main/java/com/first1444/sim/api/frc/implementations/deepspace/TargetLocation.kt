package com.first1444.sim.api.frc.implementations.deepspace

enum class TargetLocation(
        val bayType: BayType
) {
    LOADING_STATION(BayType.LOADING_STATION),
    CARGO_SHIP_FRONT(BayType.CARGO_SHIP),
    CARGO_SHIP_1(BayType.CARGO_SHIP),
    CARGO_SHIP_2(BayType.CARGO_SHIP),
    CARGO_SHIP_3(BayType.CARGO_SHIP),
    ROCKET_NEAR(BayType.ROCKET_HATCH),
    ROCKET_FAR(BayType.ROCKET_HATCH),
    ROCKET_FRONT(BayType.ROCKET_CARGO);
}