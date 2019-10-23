package com.first1444.sim.gdx.implementations.deepspace2019

import com.badlogic.gdx.physics.box2d.*
import com.first1444.sim.api.MeasureUtil.inchesToMeters
import com.first1444.sim.gdx.GdxUtil.GDX_ZERO

object Field {
    private val WIDTH_METERS: Float = inchesToMeters(27 * 12f)
    private val LENGTH_METERS: Float = inchesToMeters(54 * 12f)

    @JvmStatic
    fun createField(world: World){
        val hw = (WIDTH_METERS / 2.0f)
        val hl = (LENGTH_METERS / 2.0f)
        world.createBody(BodyDef()).createFixture(FixtureDef().apply {
            shape = EdgeShape().apply {
                set(-hw, -hl, hw, -hl) // bottom
            }
        })
        world.createBody(BodyDef()).createFixture(FixtureDef().apply {
            shape = EdgeShape().apply {
                set(-hw, hl, hw, hl) // top
            }
        })
        world.createBody(BodyDef()).createFixture(FixtureDef().apply {
            shape = EdgeShape().apply {
                set(-hw, -hl, -hw, hl)
            }
        })
        world.createBody(BodyDef()).createFixture(FixtureDef().apply {
            shape = EdgeShape().apply {
                set(hw, -hl, hw, hl)
            }
        })
    }
}
