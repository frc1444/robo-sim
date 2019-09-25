package com.first1444.sim.api.frc

import com.first1444.sim.api.ActiveMode

enum class FrcMode : ActiveMode {
    DISABLED,
    TELEOP,
    AUTONOMOUS,
    TEST
}