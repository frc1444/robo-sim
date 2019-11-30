package com.first1444.sim.api

import com.first1444.sim.api.Transform2.Companion.fromDegrees
import com.first1444.sim.api.Transform2.Companion.fromRadians
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.math.sqrt

internal class Transform2Test {
    @Test
    fun `test simple reversed`(){
        val transform = fromRadians(-15.0, -15.0, 0.0)
        assertEquals(fromRadians(Vector2(15.0, 15.0), 0.0), transform.reversed)
        assertEquals(transform, transform.reversed.reversed)
    }
    @Test
    fun `test reversed transform with angle`(){
        val transform = fromRadians(Vector2(1.0, 1.0), Math.PI / 4)
        println(transform)
        println(transform.reversed)
        assertTrue(fromRadians(Vector2(-sqrt(2.0), 0.0), -Math.PI / 4).epsilonEquals(transform.reversed))
    }
    @Test
    fun `test angle input`(){
        assertEquals(-180.0, fromDegrees(Vector2.ZERO, 180.0).rotationDegrees)
        assertEquals(-180.0, fromRadians(Vector2.ZERO, Math.PI).rotationDegrees)
        assertEquals(0.0, fromDegrees(Vector2.ZERO, 0.0).rotationDegrees)
        assertEquals(0.0, fromDegrees(Vector2.ZERO, 360.0).rotationDegrees)
    }
}