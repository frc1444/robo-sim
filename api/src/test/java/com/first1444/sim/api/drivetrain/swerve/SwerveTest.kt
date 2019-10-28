package com.first1444.sim.api.drivetrain.swerve

import com.first1444.sim.api.event.EventHandler
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.lang.Math.toRadians

class SwerveTest {
    @Test
    fun testStraightDrive(){
        val data = createDataDegrees(
                1.0, 0.0,
                1.0, 0.0,
                1.0, 0.0,
                1.0, 0.0
        )
        val drive = FourWheelSwerveDrive(data)
        drive.setControl(1.0, 0.0, 0.0, 1.0)
        drive.run()
    }
    @Test
    fun testStrafeDrive(){
        val data = createDataDegrees(
                1.0, -90.0,
                1.0, -90.0,
                1.0, -90.0,
                1.0, -90.0
        )
        val drive = FourWheelSwerveDrive(data)
        drive.setControl(0.0, 1.0, 0.0, 1.0)
        drive.run()
    }
    @Test
    fun testRotateInPlace(){
        // rotate counter clock wise
        val data = createDataDegrees(
                1.0, 45.0,
                1.0, 90 + 45.0,
                1.0, 180 + 45.0 - 360,
                1.0, 270 + 45.0 - 360
        )
        val drive = FourWheelSwerveDrive(data)
        drive.setControl(0.0, 0.0, -1.0, 1.0)
        drive.run()
    }

    private fun createDataDegrees(
        frSpeed: Double, frAngle: Double,
        flSpeed: Double, flAngle: Double,
        rlSpeed: Double, rlAngle: Double,
        rrSpeed: Double, rrAngle: Double
    ): FourWheelSwerveDriveData {
        return FourWheelSwerveDriveData(
            TestSwerveModule("front right", frSpeed, toRadians(frAngle)),
            TestSwerveModule("front left", flSpeed, toRadians(flAngle)),
            TestSwerveModule("rear left", rlSpeed, toRadians(rlAngle)),
            TestSwerveModule("rear right", rrSpeed, toRadians(rrAngle)),
            1.0, 1.0
        )
    }
}
private class TestSwerveModule(
        override val name: String,
        private val expectedSpeed: Double,
        private val expectedAngleRadians: Double
) : SwerveModule {
    override fun run() {
    }

    override val eventHandler: EventHandler = EventHandler.DO_NOTHING

    override fun setTargetSpeed(speed: Double) {
        assertEquals(expectedSpeed, speed, 0.0001) { "$name got unexpected speed" }
    }

    override fun setTargetAngleDegrees(positionDegrees: Double) {
        throw AssertionError("Although it is normally valid to call this method, we don't expect anyone to use this while testing")
    }

    override fun setTargetAngleRadians(positionRadians: Double) {
        assertEquals(expectedAngleRadians, positionRadians, 0.0001) { "$name got unexpected target angle" }
    }

    override val currentAngleDegrees: Double
        get() = throw UnsupportedOperationException()
    override val currentAngleRadians: Double
        get() = throw UnsupportedOperationException()
    override val distanceTraveledMeters: Double
        get() = throw UnsupportedOperationException()

}
