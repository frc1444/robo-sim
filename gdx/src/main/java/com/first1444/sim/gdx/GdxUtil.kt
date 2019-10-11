package com.first1444.sim.gdx

import com.first1444.sim.api.Vector2
import kotlin.math.cos
import kotlin.math.sin

val Vector2.gdx: com.badlogic.gdx.math.Vector2
    get() = com.badlogic.gdx.math.Vector2(x.toFloat(), y.toFloat())

fun Vector2.copyTo(vector2: com.badlogic.gdx.math.Vector2): com.badlogic.gdx.math.Vector2 {
    vector2.set(x.toFloat(), y.toFloat())
    return vector2
}
val com.badlogic.gdx.math.Vector2.sim: Vector2
    get() = Vector2(x.toDouble(), y.toDouble())

fun com.badlogic.gdx.math.Vector2.set(vector2: Vector2): com.badlogic.gdx.math.Vector2 = set(vector2.x.toFloat(), vector2.y.toFloat())

object GdxUtil {
    @JvmStatic
    val GDX_ZERO = com.badlogic.gdx.math.Vector2.Zero

    @JvmStatic
    fun gdxVector(x: Float, y: Float): com.badlogic.gdx.math.Vector2 {
        return com.badlogic.gdx.math.Vector2(x, y)
    }
    @JvmStatic
    @JvmOverloads
    fun gdxVectorFromRadians(angleRadians: Float, magnitude: Float = 1.0f): com.badlogic.gdx.math.Vector2 {
        return gdxVector(cos(angleRadians), sin(angleRadians)).scl(magnitude)
    }

}
