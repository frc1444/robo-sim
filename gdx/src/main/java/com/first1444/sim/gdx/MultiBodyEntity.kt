package com.first1444.sim.gdx

import com.badlogic.gdx.physics.box2d.Body

interface MultiBodyEntity : Entity {
    val bodies: List<Body>
}
