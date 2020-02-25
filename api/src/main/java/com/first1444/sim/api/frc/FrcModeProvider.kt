package com.first1444.sim.api.frc

interface FrcModeProvider {
    val mode: FrcMode
        get() = controlWord.mode
    val controlWord: ControlWord
}
