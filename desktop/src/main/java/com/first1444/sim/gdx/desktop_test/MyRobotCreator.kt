package com.first1444.sim.gdx.desktop_test

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef
import com.first1444.sim.api.MeasureUtil
import com.first1444.sim.api.RunnableCreator
import com.first1444.sim.api.Vector2
import com.first1444.sim.api.drivetrain.swerve.FourWheelSwerveDriveData
import com.first1444.sim.api.drivetrain.swerve.SwerveModule
import com.first1444.sim.gdx.*
import com.first1444.sim.gdx.drivetrain.swerve.BodySwerveModule
import com.first1444.sim.gdx.entity.ActorBodyEntity
import com.first1444.sim.gdx.entity.EntityOrientation
import com.first1444.sim.gdx.init.RobotCreator
import com.first1444.sim.gdx.init.UpdateableCreator
import com.first1444.sim.gdx.velocity.AccelerateSetPointHandler
import me.retrodaredevil.controller.gdx.GdxControllerPartCreator
import me.retrodaredevil.controller.gdx.IndexedControllerProvider
import me.retrodaredevil.controller.implementations.BaseStandardControllerInput
import me.retrodaredevil.controller.implementations.mappings.DefaultStandardControllerInputCreator
import me.retrodaredevil.controller.options.OptionValues

object MyRobotCreator : RobotCreator {
    override fun create(data: RobotCreator.Data, updateableData: UpdateableCreator.Data): Updateable {

        val maxVelocity = 3.35
        val wheelBase = MeasureUtil.inchesToMeters(22.75) // length
        val trackWidth = MeasureUtil.inchesToMeters(24.0)
        val entity = ActorBodyEntity(updateableData.contentStage, updateableData.worldManager.world, BodyDef().apply {
            type = BodyDef.BodyType.DynamicBody
            angle = 90 * MathUtils.degreesToRadians // start at 90 degrees to make this easy on the player. We will eventually add field centric controls
        }, listOf(FixtureDef().apply {
            restitution = .2f
            shape = PolygonShape().apply {
                setAsBox((wheelBase / 2).toFloat(), (trackWidth / 2).toFloat(), ZERO, 0.0f)
            }
            val area = wheelBase * trackWidth
            density = 1.0f / area.toFloat()
        }))
        val wheelBody = BodyDef().apply {
            type = BodyDef.BodyType.DynamicBody
        }
        val wheelFixture = FixtureDef().apply {
            val wheelDiameter = MeasureUtil.inchesToMeters(4.0f)
            val wheelWidth = MeasureUtil.inchesToMeters(1.0f)
            val area = wheelDiameter * wheelWidth
            shape = PolygonShape().apply {
                setAsBox(wheelDiameter / 2, wheelWidth / 2, ZERO, 0.0f) // 4 inches by 1 inch
            }
            density = 1.0f / area
        }
        val frPosition = Vector2(wheelBase / 2, -trackWidth / 2)
        val flPosition = Vector2(wheelBase / 2, trackWidth / 2)
        val rlPosition = Vector2(-wheelBase / 2, trackWidth / 2)
        val rrPosition = Vector2(-wheelBase / 2, -trackWidth / 2)

        val moduleList = ArrayList<SwerveModule>(4)
        for((moduleName, position) in listOf(
                Pair("front right", frPosition),
                Pair("front left", flPosition),
                Pair("rear left", rlPosition),
                Pair("rear right", rrPosition))){
            val wheelEntity = ActorBodyEntity(updateableData.contentStage, updateableData.worldManager.world, wheelBody, listOf(wheelFixture))
            wheelEntity.setPosition(position)
            val joint = RevoluteJointDef().apply {
                bodyA = entity.body
                bodyB = wheelEntity.body
                localAnchorA.set(position)
                localAnchorB.set(0.0f, 0.0f)
                referenceAngle = 0.0f
            }
            updateableData.worldManager.world.createJoint(joint)
            val module = BodySwerveModule(
                    moduleName, wheelEntity.body, entity.body, maxVelocity, updateableData.clock, data.driverStation,
                    AccelerateSetPointHandler(maxVelocity.toFloat() / .5f, maxVelocity.toFloat() / .2f),
                    AccelerateSetPointHandler(MathUtils.PI2 / .5f)
            )
            moduleList.add(module)
        }
        val swerveDriveData = FourWheelSwerveDriveData(
                moduleList[0], moduleList[1], moduleList[2], moduleList[3],
                wheelBase, trackWidth
        )
        val provider = IndexedControllerProvider(0)
        val creator = GdxControllerPartCreator(provider, true)
        val joystick = BaseStandardControllerInput(DefaultStandardControllerInputCreator(), creator, OptionValues.createImmutableBooleanOptionValue(true), OptionValues.createImmutableBooleanOptionValue(false))
        val robotCreator = RunnableCreator.wrap {
            Robot(data.driverStation, updateableData.clock, swerveDriveData, EntityOrientation(entity), joystick)
        }
        return UpdateableMultiplexer(listOf(
                entity,
                RobotUpdateable(robotCreator)
        ))
    }

}