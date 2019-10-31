package com.first1444.sim.gdx.ui

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.utils.viewport.Viewport


class UIViewport(
        private val minimumVirtualSize: Float
) : Viewport() {

    init {
        camera = OrthographicCamera()
    }

    override fun update(screenWidth: Int, screenHeight: Int, centerCamera: Boolean) {
        setScreenBounds(0, 0, screenWidth, screenHeight)
        if (screenWidth > screenHeight) {
            val height = minimumVirtualSize
            val width = screenWidth * height / screenHeight

            setWorldSize(width, height)
        } else {
            val width = minimumVirtualSize
            val height = screenHeight * width / screenWidth
            setWorldSize(width, height)
        }
        apply(centerCamera)
    }
}
