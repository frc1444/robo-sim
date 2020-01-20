package com.first1444.sim.api.drivetrain.swerve

import com.first1444.sim.api.Rotation2
import com.first1444.sim.api.Vector2
import com.first1444.sim.api.event.EventHandler
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.lang.Math.toRadians
import kotlin.math.sqrt

class SwerveTest {
    @Test
    fun testStraightDrive(){
        val data = createDataDegrees(
                1.0, 0.0,
                1.0, 0.0,
                1.0, 0.0,
                1.0, 0.0
        )
        for(drive in createDrives(data)) {
            drive.setControl(Vector2.X, 0.0, 1.0)
            drive.run()
        }
    }
    @Test
    fun testStrafeDrive(){
        val data = createDataDegrees(
                1.0, -90.0,
                1.0, -90.0,
                1.0, -90.0,
                1.0, -90.0
        )
        for(drive in createDrives(data)) {
            drive.setControl(-Vector2.Y, 0.0, 1.0)
            drive.run()
        }
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
        for(drive in createDrives(data)) {
            drive.setControl(Vector2.ZERO, -1.0, 1.0)
            drive.run()
        }
    }
    @Test
    fun testComplexRotateInPlace(){
        // rotate counter clock wise
        val data = createDataDegrees(
                1.0, 30.0,
                1.0, 90 + 60.0,
                1.0, 180 + 30.0 - 360,
                1.0, 270 + 60.0 - 360,
                wheelBase = 1.0, trackWidth = sqrt(3.0)
        )
        for(drive in createDrives(data)) {
            drive.setControl(Vector2.ZERO, -1.0, 1.0)
            drive.run()
        }
    }

    private fun createDataDegrees(
        frSpeed: Double, frAngle: Double,
        flSpeed: Double, flAngle: Double,
        rlSpeed: Double, rlAngle: Double,
        rrSpeed: Double, rrAngle: Double,
        wheelBase: Double = 1.0, trackWidth: Double = 1.0
    ): FourWheelSwerveDriveData {
        return FourWheelSwerveDriveData(
            TestSwerveModule("front right", frSpeed, toRadians(frAngle)),
            TestSwerveModule("front left", flSpeed, toRadians(flAngle)),
            TestSwerveModule("rear left", rlSpeed, toRadians(rlAngle)),
            TestSwerveModule("rear right", rrSpeed, toRadians(rrAngle)),
            wheelBase, trackWidth
        )
    }
    private fun convert(data: FourWheelSwerveDriveData): AnyWheelSwerveDriveData {
        val x = data.wheelBase / 2.0
        val y = data.trackWidth / 2.0
        return AnyWheelSwerveDriveData(listOf(
                PositionSwerveModule(data.frontRight, Vector2(x, -y)),
                PositionSwerveModule(data.frontLeft, Vector2(x, y)),
                PositionSwerveModule(data.rearLeft, Vector2(-x, y)),
                PositionSwerveModule(data.rearRight, Vector2(-x, -y))
        ))
    }
    private fun createDrives(data: FourWheelSwerveDriveData): List<SwerveDrive> {
        return listOf(FourWheelSwerveDrive(data), AnyWheelSwerveDrive(convert(data)))
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
    override fun setTargetAngle(position: Rotation2) {
        throw AssertionError("Although it is normally valid to call this method, we don't expect anyone to use this while testing")
    }
    override fun setTargetAngleDegrees(positionDegrees: Double) {
        throw AssertionError("Although it is normally valid to call this method, we don't expect anyone to use this while testing")
    }

    override fun setTargetAngleRadians(positionRadians: Double) {
        assertEquals(expectedAngleRadians, positionRadians, 0.0001) { "$name got unexpected target angle" }
    }

    override val currentAngle: Rotation2
        get() = throw UnsupportedOperationException()
    override val currentAngleDegrees: Double
        get() = throw UnsupportedOperationException()
    override val currentAngleRadians: Double
        get() = throw UnsupportedOperationException()
    override val distanceTraveledMeters: Double
        get() = throw UnsupportedOperationException()

}
