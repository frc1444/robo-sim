package com.first1444.sim.gdx.desktop_test

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.first1444.sim.api.MeasureUtil.inchesToMeters
import com.first1444.sim.api.Vector2
import com.first1444.sim.api.drivetrain.swerve.FourWheelSwerveDriveData
import com.first1444.sim.api.drivetrain.swerve.SwerveModule
import com.first1444.sim.api.frc.sim.MutableFrcDriverStation
import com.first1444.sim.gdx.*
import com.first1444.sim.gdx.GdxUtil.GDX_ZERO
import com.first1444.sim.gdx.GdxUtil.gdxVector
import com.first1444.sim.gdx.drivetrain.swerve.BodySwerveModule
import com.first1444.sim.gdx.implementations.deepspace2019.CargoEntity
import com.first1444.sim.gdx.implementations.deepspace2019.Field
import com.first1444.sim.gdx.render.RenderableMultiplexer
import com.first1444.sim.gdx.render.ResetRenderable
import com.first1444.sim.gdx.render.StageRenderable
import com.first1444.sim.gdx.render.WorldDebugRenderable
import com.first1444.sim.gdx.velocity.AccelerateSetPointHandler
import me.retrodaredevil.controller.gdx.GdxControllerPartCreator
import me.retrodaredevil.controller.gdx.IndexedControllerProvider
import me.retrodaredevil.controller.implementations.BaseStandardControllerInput
import me.retrodaredevil.controller.implementations.mappings.DefaultStandardControllerInputCreator
import me.retrodaredevil.controller.options.OptionValues

class TestMain : Game() {
    override fun create() {
        val clock = UpdateableClock()
        val viewport = object : ExtendViewport(10.0f, 20.0f) {
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
        val entity = ActorBodyEntity(contentStage, worldManager.world, BodyDef().apply{
            type = BodyDef.BodyType.DynamicBody
            angle = 90 * MathUtils.degreesToRadians // start at 90 degrees to make this easy on the player. We will eventually add field centric controls
//            linearDamping = 5.0f
//            angularDamping = 20.0f
        }, listOf(FixtureDef().apply{
            restitution = .2f
            shape = PolygonShape().apply {
                setAsBox((wheelBase / 2).toFloat(), (trackWidth / 2).toFloat(), GDX_ZERO, 0.0f)
            }
            val area = wheelBase * trackWidth
            density = 1.0f / area.toFloat()
        }))
        val wheelBody = BodyDef().apply {
            type = BodyDef.BodyType.DynamicBody
        }
        val wheelFixture = FixtureDef().apply {
            val wheelDiameter = inchesToMeters(4.0f)
            val wheelWidth = inchesToMeters(1.0f)
            val area = wheelDiameter * wheelWidth
            shape = PolygonShape().apply {
                setAsBox(wheelDiameter / 2, wheelWidth / 2, GDX_ZERO, 0.0f) // 4 inches by 1 inch
            }
            density = 1.0f / area
        }
        val frPosition = Vector2(wheelBase / 2, -trackWidth / 2)
        val flPosition = Vector2(wheelBase / 2, trackWidth / 2)
        val rlPosition = Vector2(-wheelBase / 2, trackWidth / 2)
        val rrPosition = Vector2(-wheelBase / 2, -trackWidth / 2)

        val moduleList = ArrayList<SwerveModule>(4)
        val updateableList = mutableListOf<Updateable>()
        for((moduleName, position) in listOf(
                Pair("front right", frPosition),
                Pair("front left", flPosition),
                Pair("rear left", rlPosition),
                Pair("rear right", rrPosition))){
            val wheelEntity = ActorBodyEntity(contentStage, worldManager.world, wheelBody, listOf(wheelFixture))
            wheelEntity.setPosition(position)
            val joint = RevoluteJointDef().apply {
                bodyA = entity.body
                bodyB = wheelEntity.body
                localAnchorA.set(position)
                localAnchorB.set(0.0f, 0.0f)
                referenceAngle = 0.0f
            }
            worldManager.world.createJoint(joint)
            val module = BodySwerveModule(
                    moduleName, wheelEntity.body, entity.body, maxVelocity, clock,
                    AccelerateSetPointHandler(maxVelocity.toFloat() / .5f, maxVelocity.toFloat() / .2f),
                    AccelerateSetPointHandler(MathUtils.PI2 / .5f)
            )
            moduleList.add(module)
//            updateableList.add(Updateable {
//                wheelEntity.rotationRadians = module.currentAngleRadians.toFloat() + entity.rotationRadians
//            })
        }
        val swerveDriveData = FourWheelSwerveDriveData(
                moduleList[0], moduleList[1], moduleList[2], moduleList[3],
                wheelBase, trackWidth
        )
//        val orientation = DefaultMutableOrientation(EntityOrientation(entity))
//        orientation.orientationDegrees = 90.0 // we start at 90 degrees

        for(i in 0..20) {
            val cargo = CargoEntity(contentStage, worldManager.world)
            cargo.position = gdxVector(3.0f, 3.0f)
            updateableList.add(cargo)
        }
        Field.createField(worldManager.world)
        Field.createRocket(worldManager.world).setTransform(-Field.FIELD_WIDTH_METERS / 2, inchesToMeters(-96.0f), 0.0f)
        Field.createRocket(worldManager.world).setTransform(Field.FIELD_WIDTH_METERS / 2, inchesToMeters(-96.0f), MathUtils.PI)
        Field.createRocket(worldManager.world).setTransform(-Field.FIELD_WIDTH_METERS / 2, inchesToMeters(96.0f), 0.0f)
        Field.createRocket(worldManager.world).setTransform(Field.FIELD_WIDTH_METERS / 2, inchesToMeters(96.0f), MathUtils.PI)
        Field.createCargoShip(worldManager.world).setTransform(0f, 0f, 0f)
        Field.createCargoShip(worldManager.world).setTransform(0f, 0f, MathUtils.PI)
        Field.createHab(worldManager.world).setTransform(0f, -Field.FIELD_LENGTH_METERS / 2, 0f)
        Field.createHab(worldManager.world).setTransform(0f, Field.FIELD_LENGTH_METERS / 2, MathUtils.PI)

        val provider = IndexedControllerProvider(0)
        val creator = GdxControllerPartCreator(provider, true)
        val joystick = BaseStandardControllerInput(DefaultStandardControllerInputCreator(), creator, OptionValues.createImmutableBooleanOptionValue(true), OptionValues.createImmutableBooleanOptionValue(false))
        val driverStation = MutableFrcDriverStation()
        val robot = Robot(driverStation, clock, swerveDriveData, EntityOrientation(entity), joystick)
        setScreen(SimpleScreen(
                UpdateableMultiplexer(listOf(
                        clock,
                        Updateable.wrap(robot),
                        entity, UpdateableMultiplexer(updateableList),
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
