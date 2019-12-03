package com.first1444.sim.api.distance

import com.first1444.sim.api.Vector2
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class DistanceAccumulatorTest {
    companion object {
        private val DISTANCE_ACCUMULATOR = object : DistanceAccumulator {
            override val position: Vector2
                get() = Vector2(56.0, 101.0) // just a random value we can work with
            override fun run() {
            }

        }
    }
    @Test
    fun testDefaultMutableDistanceAccumulator(){
        val accumulator = DefaultMutableDistanceAccumulator(DISTANCE_ACCUMULATOR, false)
        assertEquals(Vector2(56.0, 101.0), accumulator.position)
        accumulator.position = Vector2(5.0 , 6.0)
        assertEquals(Vector2(5.0, 6.0), accumulator.position)
    }
}
