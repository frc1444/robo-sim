package com.first1444.sim.wpi

import com.first1444.sim.api.Clock
import edu.wpi.first.wpilibj.Timer

class WpiClock : Clock {
    override val timeSeconds: Double
        get() = Timer.getFPGATimestamp()

}
