package com.first1444.sim.gdx.entity

import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.Touchable

open class ActorBodyEntity(
        val group: Group,
        final override val body: Body
) : BodyEntity {

    constructor(stage: Stage, body: Body) : this(
            Group().apply {
                stage.addActor(this)
                touchable = Touchable.childrenOnly
            },
            body
    )
    constructor(stage: Stage, world: World, bodyDef: BodyDef, fixtureDefs: List<FixtureDef>) : this(
            stage,
            world.createBody(bodyDef).apply {
                for(fixtureDef in fixtureDefs){
                    createFixture(fixtureDef)
                }
            }
    )
    constructor(stage: Stage, world: World, bodyDef: BodyDef) : this(stage, world, bodyDef, emptyList())

    override fun update(delta: Float) {
        val position = body.position
        group.setPosition(position.x, position.y)
    }

    override fun onRemove() {
        body.world.destroyBody(body)
        group.remove()
    }
    @Throws(Exception::class) // may be overridden, so allow the option of throwing an exception
    override fun close() {
    }

}
