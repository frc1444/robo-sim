package com.first1444.sim.gdx.implementations.deepspace2019

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.physics.box2d.*
import com.first1444.sim.api.frc.implementations.deepspace.Field2019
import com.first1444.sim.api.inchesToMeters
import com.first1444.sim.gdx.ZERO
import com.first1444.sim.gdx.gdxVector
import com.first1444.sim.gdx.set
import com.first1444.sim.gdx.setTransformRadians

object FieldSetup2019 {
    @JvmField
    val FIELD_WIDTH_METERS: Float = Field2019.WIDTH.toFloat()
    @JvmField
    val FIELD_LENGTH_METERS: Float = Field2019.LENGTH.toFloat()

    @JvmStatic
    fun createVisionTargets(world: World){
        for(target in Field2019.ALL_VISION_TARGETS){
            val transform = target.transform
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

    @JvmStatic
    fun createField(world: World){
        createFieldBounds(world)
        createRocket(world).setTransformRadians(Field2019.ROCKET_LEFT_POSITION, 0.0) // left alliance
        createRocket(world).setTransformRadians(Field2019.ROCKET_RIGHT_POSITION, Math.PI) // right alliance
        createRocket(world).setTransformRadians(Field2019.ROCKET_LEFT_POSITION.times(1.0, -1.0), 0.0) // left enemy
        createRocket(world).setTransformRadians(Field2019.ROCKET_RIGHT_POSITION.times(1.0, -1.0), Math.PI) // right enemy
        createCargoShip(world).setTransform(0f, 0f, 0f)
        createCargoShip(world).setTransform(0f, 0f, MathUtils.PI)
        createMiddleBarrier(world)
        createHab(world).setTransform(0f, -FIELD_LENGTH_METERS / 2, 0f)
        createHab(world).setTransform(0f, FIELD_LENGTH_METERS / 2, MathUtils.PI)
    }

    @JvmStatic
    fun createRocket(world: World): Body {
        return world.createBody(BodyDef()).apply {
            createFixture(FixtureDef().apply {
                shape = PolygonShape().apply {
                    set(floatArrayOf(
                            inchesToMeters(8.83f), inchesToMeters((46.5f - 9.5f) / 2),
                            inchesToMeters(8.83f), inchesToMeters(-(46.5f - 9.5f) / 2),
                            inchesToMeters(26.36f), inchesToMeters(-(37f - 19f) / 2),
                            inchesToMeters(26.36f), inchesToMeters((37f - 19f) / 2),
                            inchesToMeters(8.83f), inchesToMeters((46.5f - 9.5f) / 2)
                    ))
                }
            })
            createFixture(FixtureDef().apply {
                isSensor = true
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
        val bumperWidth = Field2019.CARGO_SHIP_BUMPER_WIDTH.toFloat()
        val bumperLength = Field2019.CARGO_SHIP_BUMPER_LENGTH.toFloat()

        val totalWidth = Field2019.CARGO_SHIP_TOTAL_WIDTH.toFloat()
        val totalLength = Field2019.CARGO_SHIP_TOTAL_LENGTH.toFloat()

        return world.createBody(BodyDef()).apply {
            createFixture(FixtureDef().apply {
                shape = PolygonShape().apply {
                    setAsBox(bumperWidth / 2.0f, bumperLength / 2.0f, gdxVector(0f, -bumperLength / 2.0f - inchesToMeters(9.0f)), 0.0f)
                }
                friction = 0.7f
            })
            createFixture(FixtureDef().apply {
                shape = PolygonShape().apply {
                    set(floatArrayOf(
                            -totalWidth / 2, inchesToMeters(-9f),
                            totalWidth / 2, inchesToMeters(-9f),
                            totalWidth / 2, inchesToMeters(-9f + 40.5f - 21.75f / 2) - totalLength,
                            bumperWidth / 2, inchesToMeters(-9f) - totalLength,
                            -bumperWidth / 2, inchesToMeters(-9f) - totalLength,
                            -totalWidth / 2, inchesToMeters(-9f + 40.5f - 21.75f / 2) - totalLength,
                            -totalWidth / 2, inchesToMeters(-9f)
                    ))
                }
            })
        }
    }
    @JvmStatic
    fun createMiddleBarrier(world: World): Body {
        val halfHeight = Field2019.CARGO_SHIP_HALF_GAP.toFloat()
        val bumperWidth = Field2019.CARGO_SHIP_BUMPER_WIDTH.toFloat()
        val totalWidth = Field2019.CARGO_SHIP_TOTAL_WIDTH.toFloat()
        return world.createBody(BodyDef()).apply {
            createFixture(FixtureDef().apply {
                shape = PolygonShape().apply {
                    setAsBox(bumperWidth / 2, halfHeight, ZERO, 0.0f)
                }
            })
            createFixture(FixtureDef().apply {
                shape = PolygonShape().apply {
                    setAsBox(totalWidth / 2, halfHeight, ZERO, 0.0f)
                }
            })
        }
    }
    @JvmStatic
    fun createHab(world: World): Body {
        val length = inchesToMeters(45f)
        val width = inchesToMeters(128f)
//        val level2Width = inchesToMeters(40.0f)
        val level3Width = inchesToMeters(48.0f)
        return world.createBody(BodyDef()).apply {
            val position = gdxVector(0f, length / 2)
            val halfCargoBay = (Field2019.HAB_CARGO_BAY_EXTEND / 2).toFloat()
            createFixture(FixtureDef().apply { // cargo bay left
                shape = PolygonShape().apply {
                    setAsBox(halfCargoBay, length / 2, position.cpy().add(-width / 2 - halfCargoBay, 0f), 0.0f)
                }
            })
            createFixture(FixtureDef().apply { // cargo bay right
                shape = PolygonShape().apply {
                    setAsBox(halfCargoBay, length / 2, position.cpy().add(width / 2 + halfCargoBay, 0f), 0.0f)
                }
            })
            createFixture(FixtureDef().apply { // main hab
                shape = PolygonShape().apply {
                    setAsBox(width / 2, length / 2, position, 0.0f)
                }
            })
            createFixture(FixtureDef().apply { // hab 3
                shape = PolygonShape().apply {
                    setAsBox(level3Width / 2, length / 2, position, 0.0f)
                }
            })
            createFixture(FixtureDef().apply {
                isSensor = true
                shape = PolygonShape().apply {
                    val halfExtend = Field2019.HAB_LEVEL_1_LENGTH.toFloat() / 2
                    setAsBox(width / 2, halfExtend, gdxVector(0f, length + halfExtend), 0.0f)
                }
            })
            createFixture(FixtureDef().apply {
                isSensor = true
                shape = PolygonShape().apply {
                    val halfExtend = (Field2019.HAB_LEVEL_1_LENGTH.toFloat() + Field2019.HAB_LIP_MEASUREMENT.toFloat()) / 2
                    setAsBox(width / 2 + Field2019.HAB_LIP_MEASUREMENT.toFloat(), halfExtend, gdxVector(0f, length + halfExtend), 0.0f)
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
