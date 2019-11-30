package com.first1444.sim.api.drivetrain.swerve

import com.first1444.sim.api.Rotation2
import com.first1444.sim.api.sensors.DefaultMutableOrientation
import com.first1444.sim.api.sensors.Orientation
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.lang.Math.toRadians

class OrientationTest {
    @Test
    fun testOrientation(){
        val o = DefaultMutableOrientation(createDegrees(37.0))
        assertEquals(37.0, o.orientationDegrees)

        o.orientationDegrees = 36.0
        assertEquals(36.0, o.orientationDegrees)

        o.orientationDegrees = 0.0
        assertEquals(0.0, o.orientationDegrees)
    }

    private fun createDegrees(valueDegrees: Double): Orientation {
        val valueRadians = toRadians(valueDegrees)
        return object : Orientation {
            override val orientation: Rotation2 = Rotation2.fromDegrees(valueDegrees)
            override val orientationDegrees: Double
                get() = valueDegrees
            override val orientationRadians: Double
                get() = valueRadians

        }
    }
}
