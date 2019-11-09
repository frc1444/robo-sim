package com.first1444.sim.gdx.implementations.deepspace2019

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.physics.box2d.*
import com.first1444.sim.api.MeasureUtil.inchesToMeters
import com.first1444.sim.api.Transform.Companion.transformRadians
import com.first1444.sim.api.Vector2
import com.first1444.sim.api.frc.implementations.deepspace.Field2019
import com.first1444.sim.gdx.gdxVector
import com.first1444.sim.gdx.set
import com.first1444.sim.gdx.setTransformRadians
import com.first1444.sim.gdx.simTransform

object FieldSetup2019 {
    @JvmField
    val FIELD_WIDTH_METERS: Float = Field2019.WIDTH.toFloat()
    @JvmField
    val FIELD_LENGTH_METERS: Float = Field2019.LENGTH.toFloat()

    @JvmStatic
    fun createVisionTargets(world: World){
        for(target in Field2019.VISION_TARGETS){
            val originalTransform = target.transform
            for(transform in listOf(originalTransform, -originalTransform)) {
                world.createBody(BodyDef().apply {
                    position.set(transform.position)
                    angle = transform.rotationRadians.toFloat()
                }).createFixture(FixtureDef().apply {
                    isSensor = true
                    shape = EdgeShape().apply {
                        set(
                                -0.03f, 0.2f,
                                -0.03f, -0.2f
                        )
                    }
                })
            }
        }
    }

    @JvmStatic
    fun createField(world: World){
        createFieldBounds(world)
        createRocket(world).setTransformRadians(Field2019.ROCKET_LEFT_POSITION, 0.0) // left alliance
        createRocket(world).setTransformRadians(Field2019.ROCKET_RIGHT_POSITION, Math.PI) // right alliance
        createRocket(world).setTransformRadians(Field2019.ROCKET_LEFT_POSITION.times(1.0, -1.0), 0.0) // left enemy
        createRocket(world).setTransformRadians(Field2019.ROCKET_RIGHT_POSITION.times(1.0, -1.0), Math.PI) // right enemy
        createCargoShip(world).setTransform(0f, 0f, 0f)
        createCargoShip(world).setTransform(0f, 0f, MathUtils.PI)
        createHab(world).setTransform(0f, -FIELD_LENGTH_METERS / 2, 0f)
        createHab(world).setTransform(0f, FIELD_LENGTH_METERS / 2, MathUtils.PI)
    }

    @JvmStatic
    fun createRocket(world: World): Body {
        return world.createBody(BodyDef()).apply {
            createFixture(FixtureDef().apply {
                val backLength = inchesToMeters(56.0f)
                val smallExtrude = inchesToMeters(8.0f)
                val fullExtrude = inchesToMeters(32.0f)
                val extrudeYPosition = (backLength - 2 * inchesToMeters(15.0f)) / 2
                shape = PolygonShape().apply {
                    set(floatArrayOf(
                            0f, backLength / 2,
                            0f, -backLength / 2,
                            smallExtrude, -backLength / 2,
                            fullExtrude, -extrudeYPosition,
                            fullExtrude, extrudeYPosition,
                            smallExtrude, backLength / 2
                    ))
                }
            })
        }
    }
    @JvmStatic
    fun createCargoShip(world: World): Body {
        val bumperLength = Field2019.CARGO_SHIP_BUMPER_LENGTH.toFloat()
        val bumperWidth = Field2019.CARGO_SHIP_BUMPER_WIDTH.toFloat()

        return world.createBody(BodyDef()).apply {
            createFixture(FixtureDef().apply {
                shape = PolygonShape().apply {
                    setAsBox(bumperWidth / 2.0f, bumperLength / 2.0f, gdxVector(0f, -bumperLength / 2.0f - inchesToMeters(9.0f)), 0.0f)
                }
                friction = 0.7f
            })
        }
    }
    @JvmStatic
    fun createHab(world: World): Body {
        val length = inchesToMeters(95.28f - 48.28f)
        val width = FIELD_WIDTH_METERS - inchesToMeters(2 * (69.56f + 27.44f))
//        val level2Width = inchesToMeters(40.0f)
        val level3Width = inchesToMeters(48.0f)
        return world.createBody(BodyDef()).apply {
            createFixture(FixtureDef().apply {
                shape = PolygonShape().apply {
                    setAsBox(width / 2, length / 2, gdxVector(0f, length / 2), 0.0f)
                }
            })
            createFixture(FixtureDef().apply {
                shape = PolygonShape().apply {
                    setAsBox(level3Width / 2, length / 2, gdxVector(0f, length / 2), 0.0f)
                }
            })
        }
    }

    @JvmStatic
    fun createFieldBounds(world: World){
        val hw = (FIELD_WIDTH_METERS / 2.0f)
        val hl = (FIELD_LENGTH_METERS / 2.0f)
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
