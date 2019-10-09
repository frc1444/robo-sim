package com.first1444.sim.gdx

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.Fixture
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.Viewport

class WorldManager : Updateable, Disposable{

    val world: World = World(Vector2.Zero, true)
    private val updateableList: MutableList<Updateable> = ArrayList()

    private val bodyArrayCache = Array<Body>()
    private val fixtureArrayCache = Array<Fixture>()

    override fun update(delta: Float) {
        for(updateable in updateableList){
            updateable.update(delta)
        }
        world.step(delta, 6, 2)
    }

    fun addUpdateable(updateable: Updateable){
        updateableList.add(updateable)
    }
    override fun dispose() {
        world.dispose()
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
