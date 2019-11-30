package com.first1444.sim.api.frc

enum class MatchType(
        val number: Int
) {
    NONE(0),
    PRACTICE(1), QUALIFICATION(2), ELIMINATION(3);

    companion object {
        @JvmStatic
        fun fromNumber(number: Int): MatchType = when(number){
            1 -> PRACTICE
            2 -> QUALIFICATION
            3 -> ELIMINATION
            else -> NONE
        }
    }
}
