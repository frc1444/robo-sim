package com.first1444.sim.api

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.math.sqrt

internal class TransformTest {
    @Test
    fun testSimpleTransform(){
        val transform = Transform.fromRadians(Vector2(-15.0, -15.0), 0.0)
        assertEquals(Transform.fromRadians(Vector2(15.0, 15.0), 0.0), transform.reversed)
        assertEquals(transform, transform.reversed.reversed)
    }
    @Test
    fun testAngleTransform(){
        val transform = Transform.fromRadians(Vector2(1.0, 1.0), Math.PI / 4)
        println(transform)
        println(transform.reversed)
        assertTrue(Transform.fromRadians(Vector2(-sqrt(2.0), 0.0), -Math.PI / 4).epsilonEquals(transform.reversed))
    }
}