package com.first1444.sim.gdx.implementations.infiniterecharge2020

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.physics.box2d.*
import com.first1444.sim.api.frc.implementations.deepspace.Field2019
import com.first1444.sim.api.frc.implementations.infiniterecharge.Field2020
import com.first1444.sim.api.inchesToMeters
import com.first1444.sim.gdx.gdxVector
import com.first1444.sim.gdx.set

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
    @JvmStatic
    @JvmOverloads
    fun createField(world: World, do2021: Boolean = true) {
        createVisionTargets(world)
        createFieldBounds(world)
        createTargetZones(world)
        createLoadingZones(world)
        createTrenchRun(world)
        createTrench(world)
        createShieldGenerator(world, do2021)
        createInitiationLine(world)
    }
    @JvmStatic
    fun createVisionTargets(world: World){
        for(target in Field2020.ALL_VISION_TARGETS){
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
    fun createFieldBounds(world: World) {
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
    fun createTargetZones(world: World) {
        world.createBody(BodyDef()).createFixture(FixtureDef().apply {
            // alliance target zone (on enemy side of field)
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
    fun createLoadingZones(world: World) {
        world.createBody(BodyDef()).createFixture(FixtureDef().apply {
            // alliance loading zone (on alliance side of field)
            isSensor = true
            shape = PolygonShape().apply {
                set(floatArrayOf(
                        -HW + inchesToMeters(94.66f + 48.0f / 2 + 74.0f), -HL,
                        -HW + inchesToMeters(94.66f + 48.0f / 2 + 74.0f + 60.0f), -HL,
                        -HW + inchesToMeters(94.66f + 48.0f / 2 + 74.0f + 60.0f / 2), -HL + inchesToMeters(30.0f)
                ))
            }
        })
        world.createBody(BodyDef()).createFixture(FixtureDef().apply {
            // enemy loading zone (on enemy side of field)
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
    fun createTrenchRun(world: World) {
        val halfLength = inchesToMeters(216.0f / 2)
        val width = inchesToMeters(55.5f)
        world.createBody(BodyDef()).createFixture(FixtureDef().apply {
            // alliance loading zone (on alliance side of field)
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
        world.createBody(BodyDef()).createFixture(FixtureDef().apply {
            // alliance loading zone (on alliance side of field)
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
    fun createTrench(world: World) {
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

    @JvmStatic
    @JvmOverloads
    fun createShieldGenerator(world: World, do2021: Boolean = true) {
        val barrierSize = inchesToMeters(12.38f)
        val zoneLength = inchesToMeters(145.99f)
        val zoneWidth = inchesToMeters(134f) // this is an estimate
//        val zoneSize = inchesToMeters(135f)

        for (angle in listOf(
                MathUtils.degreesToRadians * 22.0f, // this angle is an estimate
                MathUtils.degreesToRadians * (22.0f + 180)
        )) {
            world.createBody(BodyDef().apply {
                this.angle = angle
            }).apply {
                createFixture(FixtureDef().apply {
                    // RV zone boundary
                    isSensor = true
                    shape = PolygonShape().apply {
                        setAsBox(zoneWidth / 4, zoneLength / 2, gdxVector(zoneWidth / 4, 0f), 0f)
                    }
                })
                if (!do2021) {
                    createFixture(FixtureDef().apply {
                        // line dividing zone into two
                        isSensor = true
                        shape = EdgeShape().apply {
                            set(0f, 0f, zoneWidth / 2, 0f)
                        }
                    })
                }
                createFixture(FixtureDef().apply {
                    // top barrier
                    shape = PolygonShape().apply {
                        setAsBox(barrierSize / 2, barrierSize / 2, gdxVector(barrierSize / 2 + zoneWidth / 2, barrierSize / 2 + zoneLength / 2), 0f)
                    }
                })
                createFixture(FixtureDef().apply {
                    // lower barrier
                    shape = PolygonShape().apply {
                        setAsBox(barrierSize / 2, barrierSize / 2, gdxVector(barrierSize / 2 + zoneWidth / 2, -barrierSize / 2 - zoneLength / 2), 0f)
                    }
                })
            }
        }
    }

    @JvmStatic
    fun createInitiationLine(world: World) {
        world.createBody(BodyDef()).apply {
            createFixture(FixtureDef().apply {
                isSensor = true
                shape = EdgeShape().apply {
                    set(-HW, HL - inchesToMeters(120f), HW, HL - inchesToMeters(120f))
                }
            })
            createFixture(FixtureDef().apply {
                isSensor = true
                shape = EdgeShape().apply {
                    set(-HW, -HL + inchesToMeters(120f), HW, -HL + inchesToMeters(120f))
                }
            })
        }
    }
}
