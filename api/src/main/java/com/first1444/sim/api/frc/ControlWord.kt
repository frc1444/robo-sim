package com.first1444.sim.api.frc

class ControlWord(
        val mode: FrcMode,
        val isEmergencyStop: Boolean,
        val isFMSAttached: Boolean,
        val isDriverStationAttached: Boolean
) {
    val word: Int = run {
        var r = 0
        r = r or if(mode == FrcMode.DISABLED) 0 else 1
        r = r or if(mode == FrcMode.AUTONOMOUS) (1 shl 1) else 0
        r = r or if(mode == FrcMode.TEST) (1 shl 2) else 0
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
            var mode = FrcMode.DISABLED
            if(enabled) mode = FrcMode.TELEOP
            if(autonomous) mode = FrcMode.AUTONOMOUS
            if(test) mode = FrcMode.TEST
            return ControlWord(mode, isEmergencyStop, isFMSAttached, isDriverStationAttached)
        }
        @JvmStatic
        fun fromWord(word: Int): ControlWord {
            return create((word and 1) != 0, ((word shr 1) and 1) != 0, ((word shr 2) and 1) != 0,
                    ((word shr 3) and 1) != 0, ((word shr 4) and 1) != 0, ((word shr 5) and 1) != 0)
        }
    }

}
