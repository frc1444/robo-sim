package com.first1444.sim.api

object MeasureUtil {
    @JvmStatic
    fun inchesToMeters(inches: Double): Double = inches / 39.37008
    @JvmStatic
    fun inchesToMeters(inches: Float): Float = inches / 39.37008f

    @JvmStatic
    fun poundsToKilograms(pounds: Double): Double = pounds / 2.04623
    @JvmStatic
    fun poundsToKilograms(pounds: Float): Float = pounds / 2.04623f
}