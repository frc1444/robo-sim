package com.first1444.sim.gdx

import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.Touchable

open class ActorBox2DEntity(
        val group: Group,
        final override val body: Body
) : Box2DEntity {

    constructor(stage: Stage, world: World, bodyDef: BodyDef, fixtureDefs: List<FixtureDef>) : this(
            Group().apply {
                stage.addActor(this)
                touchable = Touchable.childrenOnly
            },
            world.createBody(bodyDef).apply {
                for(fixtureDef in fixtureDefs){
                    createFixture(fixtureDef)
                }
            }
    )

    override fun update(delta: Float) {
        val position = body.position
        group.setPosition(position.x, position.y)
    }

}
