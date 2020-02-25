package com.first1444.sim.api.frc

class ControlWord(
        val isEnabled: Boolean,
        val isAutonomous: Boolean,
        val isTest: Boolean,
        val isEmergencyStop: Boolean,
        val isFMSAttached: Boolean,
        val isDriverStationAttached: Boolean
) {
    val mode: FrcMode = if(!isEnabled) FrcMode.DISABLED else when {
        isAutonomous -> FrcMode.AUTONOMOUS
        isTest -> FrcMode.TEST
        else -> FrcMode.TELEOP
    }
    val isTeleop: Boolean = !isAutonomous && !isTest

    val isTeleopEnabled: Boolean = isTeleop && isEnabled
    val isAutonomousEnabled: Boolean = isAutonomous && isEnabled
    val isTestEnabled: Boolean = isTest && isEnabled
    val isDisabled: Boolean = !isEnabled

    val word: Int = run {
        var r = 0
        r = r or if(isEnabled) 1 else 0
        r = r or if(isAutonomous) (1 shl 1) else 0
        r = r or if(isTest) (1 shl 2) else 0
        r = r or if(isEmergencyStop) (1 shl 3) else 0
        r = r or if(isFMSAttached) (1 shl 4) else 0
        r = r or if(isDriverStationAttached) (1 shl 5) else 0
        r
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ControlWord
        return other.word == word
    }

    override fun hashCode(): Int = word

    companion object {
        @JvmStatic
        fun create(enabled: Boolean, autonomous: Boolean, test: Boolean, isEmergencyStop: Boolean, isFMSAttached: Boolean, isDriverStationAttached: Boolean): ControlWord{
            return ControlWord(enabled, autonomous, test, isEmergencyStop, isFMSAttached, isDriverStationAttached)
        }
        @JvmStatic
        fun fromWord(word: Int): ControlWord {
            return create((word and 1) != 0, ((word shr 1) and 1) != 0, ((word shr 2) and 1) != 0,
                    ((word shr 3) and 1) != 0, ((word shr 4) and 1) != 0, ((word shr 5) and 1) != 0)
        }
    }

}
