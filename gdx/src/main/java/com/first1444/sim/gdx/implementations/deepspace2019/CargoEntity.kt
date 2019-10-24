package com.first1444.sim.gdx.implementations.deepspace2019

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.CircleShape
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Stage
import com.first1444.sim.api.MeasureUtil.inchesToMeters
import com.first1444.sim.gdx.ActorBodyEntity

class CargoEntity(
        stage: Stage, world: World, bodyDef: BodyDef = BODY_DEF, fixtureDefs: List<FixtureDef> = listOf(FIXTURE_DEF)
) : ActorBodyEntity(
        stage, world, bodyDef, fixtureDefs
) {

    companion object {
        val BODY_DEF = BodyDef().apply {
            type = BodyDef.BodyType.DynamicBody
            fixedRotation = true
            linearDamping = 0.4f
        }
        val FIXTURE_DEF = FixtureDef().apply {
            density = .1f
            restitution = 0.23f // very bouncy
            shape = CircleShape(). apply {
                radius = inchesToMeters(13.0 / 2).toFloat()
            }
        }
    }
}
