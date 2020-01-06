package com.first1444.sim.api.frc.implementations.infiniterecharge

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class WheelColorTest {
    @Test
    fun testParsing(){
        assertEquals(WheelColor.YELLOW, WheelColor.parseColorOrNull("Y"))
        assertEquals(WheelColor.RED, WheelColor.parseColorOrNull("R"))
        assertEquals(WheelColor.GREEN, WheelColor.parseColorOrNull("G"))
        assertEquals(WheelColor.BLUE, WheelColor.parseColorOrNull("B"))
        assertNull(WheelColor.parseColorOrNull(""))

        assertEquals(WheelColor.YELLOW, WheelColor.parseColor('Y'))
        assertEquals(WheelColor.RED, WheelColor.parseColor('R'))
        assertEquals(WheelColor.GREEN, WheelColor.parseColor('G'))
        assertEquals(WheelColor.BLUE, WheelColor.parseColor('B'))

        assertThrows(IllegalArgumentException::class.java) { WheelColor.parseColor('A') }
    }
}
