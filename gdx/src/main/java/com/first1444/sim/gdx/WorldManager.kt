package com.first1444.sim.gdx

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.ContactListener
import com.badlogic.gdx.physics.box2d.Fixture
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.Array

class WorldManager : Updateable {

    val world: World = World(Vector2.Zero, false) // TODO when we understand sleep better, we can enable it

    private val updateables = mutableListOf<RemovableUpdateable>()

    private val bodyArrayCache = Array<Body>()
    private val fixtureArrayCache = Array<Fixture>()

    override fun update(delta: Float) {
        for(updateable in updateables){
            updateable.update(delta)
        }
        world.step(delta, 6, 2)
    }

    override fun close() {
        world.dispose()
    }

    fun add(updateable: RemovableUpdateable) {
        updateables.add(updateable)
    }
    fun remove(updateable: RemovableUpdateable): Boolean {
        val r = updateables.remove(updateable)
        if(r){
            updateable.onRemove()
        }
        return r
    }

    val bodies: Array<Body>
        get() {
            world.getBodies(bodyArrayCache)
            return bodyArrayCache
        }
    val fixtures: Array<Fixture>
        get() {
            world.getFixtures(fixtureArrayCache)
            return fixtureArrayCache
        }

}
