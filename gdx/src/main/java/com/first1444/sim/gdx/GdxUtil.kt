package com.first1444.sim.gdx

import com.first1444.sim.api.Vector2

val Vector2.gdx: com.badlogic.gdx.math.Vector2
    get() = com.badlogic.gdx.math.Vector2(x.toFloat(), y.toFloat())

fun Vector2.copyTo(vector2: com.badlogic.gdx.math.Vector2): com.badlogic.gdx.math.Vector2 {
    vector2.set(x.toFloat(), y.toFloat())
    return vector2
}
val com.badlogic.gdx.math.Vector2.sim: Vector2
    get() = Vector2(x.toDouble(), y.toDouble())

fun com.badlogic.gdx.math.Vector2.set(vector2: Vector2): com.badlogic.gdx.math.Vector2 = set(vector2.x.toFloat(), vector2.y.toFloat())

fun gdxVector(x: Float, y: Float): com.badlogic.gdx.math.Vector2{
    return com.badlogic.gdx.math.Vector2(x, y)
}
val GDX_ZERO = com.badlogic.gdx.math.Vector2.Zero
