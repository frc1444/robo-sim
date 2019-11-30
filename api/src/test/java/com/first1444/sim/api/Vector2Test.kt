package com.first1444.sim.api

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Vector2Test {

    @Test
    fun `test vector`(){
        assertEquals(4.4, Vector2(4.4, 100.0).x)
        assertEquals(4.4, Vector2(100.0, 4.4).y)

        assertEquals(Rotation2.ZERO, Vector2(1.0, 0.0).angle)
        assertEquals(0.0, Vector2(1.0, 0.0).angleDegrees)
        assertEquals(0.0, Vector2(1.0, 0.0).angleRadians)

        assertEquals(Rotation2.fromDegrees(90.0), Vector2(0.0, 1.0).angle)
        assertEquals(Rotation2.fromDegrees(180.0), Vector2(-1.0, 0.0).angle) // really -180 degrees
    }

}