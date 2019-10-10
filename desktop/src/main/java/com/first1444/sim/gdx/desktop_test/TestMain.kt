package com.first1444.sim.gdx.desktop_test

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.first1444.sim.api.MeasureUtil.inchesToMeters
import com.first1444.sim.api.MeasureUtil.poundsToKilograms
import com.first1444.sim.api.Vector2
import com.first1444.sim.api.drivetrain.swerve.FourWheelSwerveDrive
import com.first1444.sim.api.drivetrain.swerve.FourWheelSwerveDriveData
import com.first1444.sim.api.sensors.DefaultMutableOrientation
import com.first1444.sim.gdx.*
import com.first1444.sim.gdx.drivetrain.swerve.VelocitySwerveModule
import com.first1444.sim.gdx.implementations.deepspace2019.CargoEntity
import com.first1444.sim.gdx.physics.EntityVelocityApplier
import com.first1444.sim.gdx.render.RenderableMultiplexer
import com.first1444.sim.gdx.render.ResetRenderable
import com.first1444.sim.gdx.render.StageRenderable
import com.first1444.sim.gdx.render.WorldDebugRenderable

class TestMain : Game() {
    override fun create() {
        val clock = UpdateableClock()
        val viewport = object : ExtendViewport(14.0f, 14.0f) {
            override fun apply(centerCamera: Boolean) {
                super.apply(false)
            }
        }
        viewport.camera.position.set(0.0f, 0.0f, 0.0f)

        val worldManager = WorldManager()
        val contentStage = Stage(viewport)

        val maxVelocity = 3.35
        val wheelBase = inchesToMeters(22.75) // length
        val trackWidth = inchesToMeters(24.0)
        val area = wheelBase * trackWidth
        println("calculated area is: $area")
        val fr = VelocitySwerveModule("front right", Vector2(wheelBase / 2, -trackWidth / 2), maxVelocity, clock) // lower right
        val fl = VelocitySwerveModule("front left", Vector2(wheelBase / 2, trackWidth / 2), maxVelocity, clock) // upper right
        val rl = VelocitySwerveModule("rear left", Vector2(-wheelBase / 2, trackWidth / 2), maxVelocity, clock) // upper left
        val rr = VelocitySwerveModule("rear right", Vector2(-wheelBase / 2, -trackWidth / 2), maxVelocity, clock) // lower left
        val swerveDriveData = FourWheelSwerveDriveData(
                fr, fl, rl, rr,
                wheelBase, trackWidth
        )
        val swerveDrive = FourWheelSwerveDrive(swerveDriveData)

        val entity = ActorBox2DEntity(contentStage, worldManager.world, BodyDef().apply{
            type = BodyDef.BodyType.DynamicBody
            angle = 90 * MathUtils.degreesToRadians // start at 90 degrees to make this easy on the player. We will eventually add field centric controls
//            linearDamping = maxVelocity.toFloat() * slowFactor.toFloat()
            linearDamping = 5.0f
            angularDamping = 20.0f
        }, listOf(FixtureDef().apply{
//            density = poundsToKilograms(120.0).toFloat() / area.toFloat()
            density = 1.0f / area.toFloat()
            shape = PolygonShape().apply {
                setAsBox((wheelBase / 2).toFloat(), (trackWidth / 2).toFloat(), GDX_ZERO, 0.0f)
            }
        }))
        val orientation = DefaultMutableOrientation(EntityOrientation(entity))
        orientation.orientationDegrees = 90.0 // we start at 90 degrees

        val cargo = CargoEntity(contentStage, worldManager.world)
        cargo.position = gdxVector(3.0f, 3.0f)

        setScreen(SimpleScreen(
                UpdateableMultiplexer(listOf(
                        clock,
                        Updateable { delta ->
                            val up = if(Gdx.input.isKeyPressed(Input.Keys.W)) 1.0 else 0.0
                            val down = if(Gdx.input.isKeyPressed(Input.Keys.S)) 1.0 else 0.0
                            val left = if(Gdx.input.isKeyPressed(Input.Keys.A)) 1.0 else 0.0
                            val right = if(Gdx.input.isKeyPressed(Input.Keys.D)) 1.0 else 0.0
                            val x = right - left
                            val y = up - down
                            var translation = Vector2(-y, x).rotateRadians(-orientation.orientationRadians)
                            if(translation.magnitude > 1){
                                translation /= translation.magnitude
                            }
                            val rotate = (if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) 1 else 0) - (if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) 1 else 0)

                            swerveDrive.setControl(translation.y, translation.x, rotate.toDouble(), 1.0)
                        },
                        Updateable.wrap(swerveDrive),
                        EntityVelocityApplier(entity, listOf(fr, fl, rl, rr)),
                        entity, cargo,
                        worldManager
                )),
                RenderableMultiplexer(listOf(
                        ResetRenderable(Color.BLACK),
                        StageRenderable(contentStage),
                        WorldDebugRenderable(worldManager.world, viewport.camera)
                )),
                object : Pauseable {
                    override fun pause() {
                    }
                    override fun resume() {
                    }
                }
        ))
    }


}
