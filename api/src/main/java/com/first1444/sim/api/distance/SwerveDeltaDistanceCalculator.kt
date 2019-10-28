package com.first1444.sim.api.distance

import com.first1444.sim.api.Vector2
import com.first1444.sim.api.drivetrain.swerve.SwerveDriveData
import kotlin.math.cos
import kotlin.math.sin

class SwerveDeltaDistanceCalculator
@JvmOverloads constructor(
        private val swerveDriveData: SwerveDriveData,
        immediatelyInitialize: Boolean = false
) : DeltaDistanceCalculator {

    private var lastDistanceArray: DoubleArray = if(immediatelyInitialize){
        getInitializedArray(swerveDriveData)
    } else {
        DoubleArray(swerveDriveData.modules.size)
    }
    private var initialized = immediatelyInitialize

    override fun getDelta(): Vector2 {
        if(!initialized){
            lastDistanceArray = getInitializedArray(swerveDriveData)
            initialized = true
            return Vector2.ZERO
        }
        var x = 0.0
        var y = 0.0
        val modules = swerveDriveData.modules
        for((i, module) in modules.withIndex()){
            val current = module.distanceTraveledMeters
            val last = lastDistanceArray[i]
            lastDistanceArray[i] = current
            val angle = module.currentAngleRadians // it might be more accurate to use the last known angle, but this should be accurate enough

            val delta = current - last
            x += cos(angle) * delta
            y += sin(angle) * delta
        }
        return Vector2(x / modules.size, y / modules.size)
    }

    companion object {
        fun getInitializedArray(swerveDriveData: SwerveDriveData): DoubleArray{
            return swerveDriveData.modules.map { it.distanceTraveledMeters }.toDoubleArray()
        }
    }

}
