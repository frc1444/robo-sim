package com.first1444.sim.api

import com.first1444.sim.api.Rotation2.Companion.ZERO
import com.first1444.sim.api.Rotation2.Companion.fromDegrees
import com.first1444.sim.api.Rotation2.Companion.fromVector
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.math.*

internal class Rotation2Test {
    @Test
    fun `test rotation`(){
        assertEquals(-180.0, validate(fromDegrees(-180.0)).degrees)
        assertEquals(-180.0, validate(fromDegrees(180.0)).degrees)
        assertEquals(0.0, validate(fromDegrees(360.0)).degrees)

        assertEquals(0.0, validate(fromVector(1e-7, 1e-7)).radians)

        assertEquals(90.0, validate(ZERO.rotate90(1)).degrees)
        assertEquals(-90.0, validate(ZERO.rotate90(-1)).degrees)
        assertEquals(-180.0, validate(ZERO.rotate90(2)).degrees)
        validate(ZERO.unaryMinus())
        validate(fromDegrees(53.4))
        validate(fromDegrees(53.4).unaryMinus())
    }
    private fun validate(rotation: Rotation2): Rotation2{
        assertEquals(rotation.radians, halfMod(atan2(rotation.sin, rotation.cos), Math.PI * 2))
        assertEquals(rotation.sin, sin(rotation.radians), 1e-15)
        assertEquals(rotation.cos, cos(rotation.radians), 1e-15)
        if(rotation.cos != 0.0) {
            assertEquals(rotation.tan, tan(rotation.radians), 1e-15)
        }
        assertEquals(1.0, hypot(rotation.cos, rotation.sin), 1e-15)
        return rotation
    }
}
