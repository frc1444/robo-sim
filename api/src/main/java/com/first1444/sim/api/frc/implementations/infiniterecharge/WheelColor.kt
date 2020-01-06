package com.first1444.sim.api.frc.implementations.infiniterecharge

/**
 * Represents one of the colors of the control panel AKA color wheel AKA wheel of fortune
 *
 * Link here [wpilib docs for 2020 game data](https://docs.wpilib.org/en/latest/docs/software/wpilib-overview/2020-Game-Data.html)
 */
enum class WheelColor(
        val character: Char
) {
    BLUE('B'),
    GREEN('G'),
    RED('R'),
    YELLOW('Y')
    ;

    companion object {
        @JvmStatic
        fun parseColorOrNull(colorString: String): WheelColor? = if (colorString.isEmpty()) null else when(colorString[0]) {
            'B' -> BLUE
            'G' -> GREEN
            'R' -> RED
            'Y' -> YELLOW
            else -> null
        }

        @JvmStatic
        fun parseColor(colorCharacter: Char): WheelColor = when(colorCharacter) {
            'B' -> BLUE
            'G' -> GREEN
            'R' -> RED
            'Y' -> YELLOW
            else -> throw IllegalArgumentException("Unknown character=$colorCharacter")
        }
    }
}
