package com.first1444.sim.gdx.implementations.infiniterecharge

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.EdgeShape
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.World
import com.first1444.sim.api.frc.implementations.infiniterecharge.Field2020
import com.first1444.sim.api.inchesToMeters

object FieldSetup2020 {
    @JvmStatic fun createField(world: World){
        createFieldBounds(world)
    }
    @JvmStatic fun createFieldBounds(world: World){
        val hw = (Field2020.WIDTH / 2).toFloat()
        val hl = (Field2020.LENGTH / 2).toFloat()
        val halfRailLength = hl - inchesToMeters(25.65f)
//        val halfRailWidth = hw - inchesToMeters(69.78f)
        val halfRailWidth = inchesToMeters(48 + 60 + 6 * 12.0f) / 2

        createLine(world, -hw, -halfRailLength, -hw, halfRailLength) // left
        createLine(world, hw, -halfRailLength, hw, halfRailLength) // right

        createLine(world, -halfRailWidth, -hl, halfRailWidth, -hl) // bottom
        createLine(world, -halfRailWidth, hl, halfRailWidth, hl) // top

        createLine(world, -hw, -halfRailLength, -halfRailWidth, -hl) // left to bottom
        createLine(world, hw, -halfRailLength, halfRailWidth, -hl) // right to bottom
        createLine(world, -hw, halfRailLength, -halfRailWidth, hl) // left to top
        createLine(world, hw, halfRailLength, halfRailWidth, hl) // right to top
    }
    private fun createLine(world: World, v1X: Float, v1Y: Float, v2X: Float, v2Y: Float) {
        world.createBody(BodyDef()).createFixture(FixtureDef().apply {
            shape = EdgeShape().apply {
                set(v1X, v1Y, v2X, v2Y)
            }
        })
    }
}
