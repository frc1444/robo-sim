package com.first1444.sim.gdx.implementations.infiniterecharge2020

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.CircleShape
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Stage
import com.first1444.sim.api.inchesToMeters
import com.first1444.sim.gdx.entity.ActorBodyEntity

class PowerCellEntity(
        stage: Stage, world: World, bodyDef: BodyDef = BODY_DEF, fixtureDefs: List<FixtureDef> = listOf(FIXTURE_DEF)
) : ActorBodyEntity(
        stage, world, bodyDef, fixtureDefs
) {

    companion object {
        val BODY_DEF = BodyDef().apply {
            type = BodyDef.BodyType.DynamicBody
            fixedRotation = true
            linearDamping = 0.5f
        }
        val FIXTURE_DEF = FixtureDef().apply {
            density = .1f
            restitution = 0.23f
            shape = CircleShape(). apply {
                radius = inchesToMeters(7.0f / 2)
            }
        }
    }
}
