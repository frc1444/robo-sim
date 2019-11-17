package com.first1444.sim.api.drivetrain

import com.first1444.sim.api.Vector2

interface StrafeDrive : Drivetrain {
    /**
     * Think of the robot pointed right (0 degrees) in a cartesian coordinate field. Calling this method does not
     * account for the orientation of the robot
     *
     * All of these values are in the range [-1..1]
     *
     * If you have a joystick with an x and y value and you want robot centric controls, you should rotate it -90 degrees (use Vector2(y, -x)) so up on the joystick becomes Vector2(1.0, 0.0)
     * @param translation The vector for translation where Vector2(1.0, 0.0) ([Vector2.X]) is forward
     * @param turnAmount A positive value makes the robot rotate clockwise, a negative value makes the robot rotate counter-clockwise
     * @param speed The speed multiplier
     */
    fun setControl(translation: Vector2, turnAmount: Double, speed: Double)
}
