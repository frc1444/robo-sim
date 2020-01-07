package com.first1444.sim.gdx.implementations.infiniterecharge

import com.badlogic.gdx.physics.box2d.*
import com.first1444.sim.api.frc.implementations.infiniterecharge.Field2020
import com.first1444.sim.api.inchesToMeters

/**
 * This has methods to help create Box2D elements to display interactive field components for the FRC 2020 field.
 *
 * Dimensions are from [FIRST's playing field page](https://www.firstinspires.org/robotics/frc/playing-field)
 */
object FieldSetup2020 {
    private const val TRENCH_CATEGORY: Short = 0b10000000000
    const val TRENCH_MASK_BITS: Short = TRENCH_CATEGORY

    private val HW = (Field2020.WIDTH / 2).toFloat()
    private val HL = (Field2020.LENGTH / 2).toFloat()
    @JvmStatic fun createField(world: World){
        createFieldBounds(world)
        createTargetZones(world)
        createLoadingZones(world)
        createTrenchRun(world)
        createTrench(world)
    }
    @JvmStatic fun createFieldBounds(world: World){
        val halfRailLength = HL - inchesToMeters(25.65f)
        val halfRailWidth = inchesToMeters(48 + 60 + 6 * 12.0f) / 2

        createLine(world, -HW, -halfRailLength, -HW, halfRailLength) // left
        createLine(world, HW, -halfRailLength, HW, halfRailLength) // right

        createLine(world, -halfRailWidth, -HL, halfRailWidth, -HL) // bottom
        createLine(world, -halfRailWidth, HL, halfRailWidth, HL) // top

        createLine(world, -HW, -halfRailLength, -halfRailWidth, -HL) // left to bottom
        createLine(world, HW, -halfRailLength, halfRailWidth, -HL) // right to bottom
        createLine(world, -HW, halfRailLength, -halfRailWidth, HL) // left to top
        createLine(world, HW, halfRailLength, halfRailWidth, HL) // right to top

    }
    private fun createLine(world: World, v1X: Float, v1Y: Float, v2X: Float, v2Y: Float) {
        world.createBody(BodyDef()).createFixture(FixtureDef().apply {
            shape = EdgeShape().apply {
                set(v1X, v1Y, v2X, v2Y)
            }
        })
    }
    @JvmStatic
    fun createTargetZones(world: World){
        world.createBody(BodyDef()).createFixture(FixtureDef().apply { // alliance target zone (on enemy side of field)
            isSensor = true
            shape = PolygonShape().apply {
                set(floatArrayOf(
                        HW - inchesToMeters(94.66f), HL - inchesToMeters(30.0f),
                        HW - inchesToMeters(94.66f + 48.0f / 2), HL,
                        HW - inchesToMeters(94.66f - 48.0f / 2), HL
                ))
            }
        })
        world.createBody(BodyDef()).createFixture(FixtureDef().apply {
            isSensor = true
            shape = PolygonShape().apply {
                set(floatArrayOf(
                        -HW + inchesToMeters(94.66f), -HL + inchesToMeters(30.0f),
                        -HW + inchesToMeters(94.66f + 48.0f / 2), -HL,
                        -HW + inchesToMeters(94.66f - 48.0f / 2), -HL
                ))
            }
        })
    }
    @JvmStatic
    fun createLoadingZones(world: World){
        world.createBody(BodyDef()).createFixture(FixtureDef().apply { // alliance loading zone (on alliance side of field)
            isSensor = true
            shape = PolygonShape().apply {
                set(floatArrayOf(
                        -HW + inchesToMeters(94.66f + 48.0f / 2 + 74.0f), -HL,
                        -HW + inchesToMeters(94.66f + 48.0f / 2 + 74.0f + 60.0f), -HL,
                        -HW + inchesToMeters(94.66f + 48.0f / 2 + 74.0f + 60.0f / 2), -HL + inchesToMeters(30.0f)
                ))
            }
        })
        world.createBody(BodyDef()).createFixture(FixtureDef().apply { // enemy loading zone (on enemy side of field)
            isSensor = true
            shape = PolygonShape().apply {
                set(floatArrayOf(
                        HW - inchesToMeters(94.66f + 48.0f / 2 + 74.0f), HL,
                        HW - inchesToMeters(94.66f + 48.0f / 2 + 74.0f + 60.0f), HL,
                        HW - inchesToMeters(94.66f + 48.0f / 2 + 74.0f + 60.0f / 2), HL - inchesToMeters(30.0f)
                ))
            }
        })
    }

    @JvmStatic
    fun createTrenchRun(world: World){
        val halfLength = inchesToMeters(216.0f / 2)
        val width = inchesToMeters(55.5f)
        world.createBody(BodyDef()).createFixture(FixtureDef().apply { // alliance loading zone (on alliance side of field)
            isSensor = true
            shape = PolygonShape().apply {
                set(floatArrayOf(
                        HW, halfLength,
                        HW - width, halfLength,
                        HW - width, -halfLength,
                        HW, -halfLength
                ))
            }
        })
        world.createBody(BodyDef()).createFixture(FixtureDef().apply { // alliance loading zone (on alliance side of field)
            isSensor = true
            shape = PolygonShape().apply {
                set(floatArrayOf(
                        -HW, -halfLength,
                        -HW + width, -halfLength,
                        -HW + width, halfLength,
                        -HW, halfLength
                ))
            }
        })
    }
    @JvmStatic
    fun createTrench(world: World){
        createLine(
                world,
                HW - inchesToMeters(55.5f), HL - inchesToMeters(369.18f),
                HW - inchesToMeters(55.5f), HL - inchesToMeters(369.18f - 29.5f)
        )
        createLine(
                world,
                -HW + inchesToMeters(55.5f), -HL + inchesToMeters(369.18f),
                -HW + inchesToMeters(55.5f), -HL + inchesToMeters(369.18f - 29.5f)
        )
        world.createBody(BodyDef()).createFixture(FixtureDef().apply {
            filter.categoryBits = TRENCH_CATEGORY
            filter.maskBits = TRENCH_MASK_BITS
            shape = PolygonShape().apply {
                set(floatArrayOf(
                        HW, HL - inchesToMeters(369.18f),
                        HW - inchesToMeters(55.5f), HL - inchesToMeters(369.18f),
                        HW - inchesToMeters(55.5f), HL - inchesToMeters(369.18f - 29.5f),
                        HW, HL - inchesToMeters(369.18f - 29.5f)
                ))
            }
        })
        world.createBody(BodyDef()).createFixture(FixtureDef().apply {
            filter.categoryBits = TRENCH_CATEGORY
            filter.maskBits = TRENCH_MASK_BITS
            shape = PolygonShape().apply {
                set(floatArrayOf(
                        -HW, -HL + inchesToMeters(369.18f),
                        -HW + inchesToMeters(55.5f), -HL + inchesToMeters(369.18f),
                        -HW + inchesToMeters(55.5f), -HL + inchesToMeters(369.18f - 29.5f),
                        -HW, -HL + inchesToMeters(369.18f - 29.5f)
                ))
            }
        })
    }
}
