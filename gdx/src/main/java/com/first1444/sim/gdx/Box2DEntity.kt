package com.first1444.sim.gdx

import com.badlogic.gdx.physics.box2d.Body
import com.first1444.sim.api.Vector2

// TODO If this doesn't work very well, we could make a JointEntity class, which would have multiple bodies constrained by joints

interface Box2DEntity : Entity {
    val body: Body

    override val position: Vector2
        get() = body.position.sim
}
