package com.first1444.sim.api

import com.first1444.sim.api.Rotation.Companion.rotationDegrees
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class RotationTest {
    @Test
    fun testRotation(){
        assertEquals(-180.0, rotationDegrees(-180.0).degrees)
        assertEquals(-180.0, rotationDegrees(180.0).degrees)
        assertEquals(0.0, rotationDegrees(360.0).degrees)
    }
}
