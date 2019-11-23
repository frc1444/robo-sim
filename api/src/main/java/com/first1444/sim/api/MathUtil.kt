@file:JvmName("MathUtil")
package com.first1444.sim.api

import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.math.sign

/**
 *
 * @param a Number on the left of the MOD
 * @param b Number on the right of the MOD
 * @return The modulus of a MOD b where the return value is not negative
 */
fun mod(a: Double, b: Double): Double {
    var r = a % b
    if (r < 0) {
        r += b
    }
    return r
}
fun mod(a: Float, b: Float): Float {
    var r = a % b
    if (r < 0) {
        r += b
    }
    return r
}
fun mod(a: Int, b: Int): Int {
    var r = a % b
    if (r < 0) {
        r += b
    }
    return r
}
fun mod(a: Long, b: Long): Long {
    var r = a % b
    if (r < 0) {
        r += b
    }
    return r
}

/**
 * returns a - b or, when |a - b| > wrap / 2, it finds a quicker way
 *
 * minChange(1, 5, 4) == 0 <br></br>
 *
 * minChange(1, 5, 5) == 1 <br></br>
 *
 * minChange(5, 1, 5) == -1 <br></br>
 *
 * minChange(30, 300, 360) == 90 <br></br>
 *
 * minChange(180, 0, 360) == 180 <br></br>
 *
 * minChange(181, 0, 360) == -179 <br></br>
 *
 * @param a Usually the desired variable to get to
 * @param b Usually the current variable to change
 * @param wrap The number that it "wraps" at. Ex: if wrap is 10, 2 is the same as 12
 * @return A positive or negative number that if added to b is the smallest change to get to a.
 */
fun minChange(a: Double, b: Double, wrap: Double): Double {
    return halfMod(a - b, wrap)
}
fun halfMod(a: Double, wrap: Double): Double {
    var aa = mod(a, wrap)
    if (aa.absoluteValue > wrap / 2.0f) {
        if (aa < 0) {
            aa += wrap
        } else {
            aa -= wrap
        }
    }
    return aa
}

/**
 * @return return Math.abs(minChange(a, b, wrap));
 */
fun minDistance(a: Double, b: Double, wrap: Double): Double {
    return minChange(a, b, wrap).absoluteValue
}

fun conservePow(a: Double, b: Double): Double {
    return a.absoluteValue.pow(b).absoluteValue * a.sign
}
