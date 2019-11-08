package com.first1444.sim.gdx

import com.badlogic.gdx.physics.box2d.Body
import com.first1444.sim.api.Transform

var Body.simTransform: Transform
    get() = transform.sim
    set(value){
        setTransform(value.x.toFloat(), value.y.toFloat(), value.rotationRadians.toFloat())
    }

var Body.gdxTransform: com.badlogic.gdx.physics.box2d.Transform
    get() = transform
    set(value) {
        val position = value.position
        setTransform(position.x, position.y, value.rotation)
    }
