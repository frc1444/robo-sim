package com.first1444.sim.api.frc

enum class DriverStationLocation(
        val locationValue: Int
) {
    LEFT(1),
    MIDDLE(2),
    RIGHT(3);
    companion object {
        @JvmStatic
        fun getDriverStationLocation(locationValue: Int): DriverStationLocation{
            return when(locationValue){
                1 -> LEFT
                2 -> MIDDLE
                3 -> RIGHT
                else -> throw IllegalArgumentException("Only values 1, 2 or 3 are allowed! locationValue: $locationValue")
            }
        }
    }
}
