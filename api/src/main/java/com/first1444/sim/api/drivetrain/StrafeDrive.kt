package com.first1444.sim.api.drivetrain

interface StrafeDrive : Drivetrain {
    /**
     * Think of the robot pointed right (0 degrees) in a cartesian coordinate field. Calling this method does not
     * account for the orientation of the robot
     *
     * All of these values are in the range [-1..1]
     * @param forward A positive value makes the robot move in the positive X direction, a negative value in the negative X direction. With robot centric controls, this is usually the Y axis of a joystick
     * @param strafe A positive value makes the robot move in the negative Y direction, a negative value in the positive Y direction. With robot centric controls, this is usually the X axis of a joystick
     * @param turnAmount A positive value makes the robot rotate clockwise, a negative value makes the robot rotate counter-clockwise
     * @param speed The speed multiplier
     */
    fun setControl(forward: Double, strafe: Double, turnAmount: Double, speed: Double)
}
