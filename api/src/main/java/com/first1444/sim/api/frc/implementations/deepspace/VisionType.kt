package com.first1444.sim.api.frc.implementations.deepspace

enum class VisionType(
        val centerHeight: Double
){
    NORMAL(Field2019.VISION_CENTER_HEIGHT), FRONT_ROCKET(Field2019.FRONT_ROCKET_VISION_CENTER_HEIGHT);
}
