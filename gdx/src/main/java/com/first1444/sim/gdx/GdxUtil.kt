@file:JvmName("GdxUtil")
package com.first1444.sim.gdx

import com.badlogic.gdx.scenes.scene2d.Event
import com.badlogic.gdx.scenes.scene2d.EventListener
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
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

fun clickDownListener(onClick: () -> Unit): EventListener = object : InputListener() {
    override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
        onClick()
        return true
    }
}
fun clickUpListener(onClick: () -> Unit): EventListener = object : InputListener() {
    override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
        return true
    }
    override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
        onClick()
    }
}
fun changeEventListener(onChange: (ChangeListener.ChangeEvent) -> Unit): EventListener = object : EventListener {
    override fun handle(event: Event): Boolean {
        if(event is ChangeListener.ChangeEvent){
            onChange(event)
            return true
        }
        return false
    }
}

@JvmField
val ZERO = com.badlogic.gdx.math.Vector2.Zero

fun gdxVector(x: Float, y: Float): com.badlogic.gdx.math.Vector2 {
    return com.badlogic.gdx.math.Vector2(x, y)
}
@JvmOverloads
fun gdxVectorFromRadians(angleRadians: Float, magnitude: Float = 1.0f): com.badlogic.gdx.math.Vector2 {
    return gdxVector(cos(angleRadians), sin(angleRadians)).scl(magnitude)
}
