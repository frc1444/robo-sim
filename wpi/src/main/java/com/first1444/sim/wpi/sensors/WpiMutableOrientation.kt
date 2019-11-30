package com.first1444.sim.wpi.sensors

import com.first1444.sim.api.Rotation2
import com.first1444.sim.api.sensors.MutableOrientation
import edu.wpi.first.wpilibj.interfaces.Gyro
import java.lang.Math.toDegrees
import java.lang.Math.toRadians

/**
 * The [MutableOrientation] implementation for [Gyro]s
 *
 * @param gyro The gyro
 * @param isGyroReversed By default true. If true, the gyro will be reversed.
 *
 * Normally this is set to true because most [Gyro]s give compass angles instead of cartesian/polar angles. If a gyro gives compass angles and you want cartesian/polar angles, this should be true. If a gyro gives cartesian/polar angles and you want compass angles, set this to true. Otherwise set this to false
 */
class WpiMutableOrientation @JvmOverloads constructor(
        private val gyro: Gyro,
        private val isGyroReversed: Boolean = true
) : MutableOrientation {

    private var offset = 0.0

    override var orientation: Rotation2
        get() = Rotation2.fromDegrees(orientationDegrees)
        set(value) { orientationDegrees = value.degrees }
    override var orientationDegrees: Double
        get() = if(isGyroReversed){
            offset - gyro.angle
        } else {
            offset + gyro.angle
        }
        set(value) {
            gyro.reset()
            offset = value
        }
    override var orientationRadians: Double
        get() = toRadians(orientationDegrees)
        set(value) {
            orientationDegrees = toDegrees(value)
        }

}
