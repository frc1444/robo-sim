package com.first1444.sim.api

object MeasureUtil {
    @JvmStatic
    fun inchesToMeters(inches: Double): Double = inches / 39.37008

    @JvmStatic
    fun poundsToKilograms(pounds: Double): Double = pounds / 2.04623
}