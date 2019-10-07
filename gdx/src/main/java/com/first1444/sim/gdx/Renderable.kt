package com.first1444.sim.gdx

import com.badlogic.gdx.utils.Disposable

/**
 * Instances of this class represent something that draws DIRECTLY to the screen. Because of this, you should make
 * sure that when you call [render], you call it in the correct order so things are drawn correctly.
 */
interface Renderable : Disposable{
    fun render(delta: Float)
    fun resize(width: Int, height: Int)
}
