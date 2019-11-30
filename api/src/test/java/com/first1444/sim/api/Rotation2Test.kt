package com.first1444.sim.api

import com.first1444.sim.api.Rotation2.Companion.fromDegrees
import com.first1444.sim.api.Rotation2.Companion.fromVector
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Rotation2Test {
    @Test
    fun `test rotation`(){
        assertEquals(-180.0, fromDegrees(-180.0).degrees)
        assertEquals(-180.0, fromDegrees(180.0).degrees)
        assertEquals(0.0, fromDegrees(360.0).degrees)

        assertEquals(0.0, fromVector(1e-7, 1e-7).radians)
    }
}
