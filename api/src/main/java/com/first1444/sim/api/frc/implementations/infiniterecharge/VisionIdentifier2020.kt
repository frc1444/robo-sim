package com.first1444.sim.api.frc.implementations.infiniterecharge

import com.first1444.sim.api.frc.implementations.VisionIdentifier

class VisionIdentifier2020(
        val visionType: VisionType2020,
        val isEnemyOwner: Boolean
) : VisionIdentifier {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as VisionIdentifier2020

        if (visionType != other.visionType) return false
        if (isEnemyOwner != other.isEnemyOwner) return false

        return true
    }

    override fun hashCode(): Int {
        var result = visionType.hashCode()
        result = 31 * result + isEnemyOwner.hashCode()
        return result
    }

    override fun toString(): String {
        return "VisionIdentifier2020(visionType=$visionType, isEnemyOwner=$isEnemyOwner)"
    }
}
