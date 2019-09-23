package com.first1444.sim.api.sensors

/**
 * An interface that communicates the orientation of the robot.
 *
 * Is is recommended that the implementation returns cartesian/polar angles and NOT compass angles.
 */
interface Orientation {
    val orientationDegrees: Double
    val orientationRadians: Double
}
