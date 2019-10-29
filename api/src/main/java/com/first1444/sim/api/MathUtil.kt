package com.first1444.sim.api

import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.math.sign


object MathUtil {
    /**
     *
     * @param a Number on the left of the MOD
     * @param b Number on the right of the MOD
     * @return The modulus of a MOD b where the return value is not negative
     */
    @JvmStatic
    fun mod(a: Double, b: Double): Double {
        var r = a % b
        if (r < 0) {
            r += b
        }
        return r
    }

    @JvmStatic
    fun mod(a: Int, b: Int): Int {
        var r = a % b
        if (r < 0) {
            r += b
        }
        return r
    }

    /**
     * returns a - b or, when |a - b| > wrap / 2, it finds a quicker way
     * <br></br> <br></br>
     * minChange(1, 5, 4) == 0 <br></br>
     * minChange(1, 5, 5) == 1 <br></br>
     * minChange(5, 1, 5) == -1 <br></br>
     * <br></br>
     * minChange(30, 300, 360) == 90 <br></br>
     * minChange(180, 0, 360) == 180 <br></br>
     * minChange(181, 0, 360) == -179 <br></br>
     * @param a Usually the desired variable to get to
     * @param b Usually the current variable to change
     * @param wrap The number that it "wraps" at. Ex: if wrap is 10, 2 is the same as 12
     * @return A positive or negative number that if added to b is the smallest change to get to a.
     */
    @JvmStatic
    fun minChange(a: Double, b: Double, wrap: Double): Double {
        val aa = mod(a, wrap)
        val bb = mod(b, wrap)

        var change = aa - bb
        if (change.absoluteValue > wrap / 2.0f) {
            if (change < 0) {
                change += wrap
            } else {
                change -= wrap
            }
        }
        return change
    }

    /**
     * @return return Math.abs(minChange(a, b, wrap));
     */
    @JvmStatic
    fun minDistance(a: Double, b: Double, wrap: Double): Double {
        return minChange(a, b, wrap).absoluteValue
    }

    @JvmStatic
    fun conservePow(a: Double, b: Double): Double {
        return a.absoluteValue.pow(b).absoluteValue * a.sign
    }
}