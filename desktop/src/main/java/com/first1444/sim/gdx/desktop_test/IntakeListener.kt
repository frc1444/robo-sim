package com.first1444.sim.gdx.desktop_test

import com.badlogic.gdx.physics.box2d.*
import com.first1444.sim.gdx.WorldManager
import com.first1444.sim.gdx.implementations.infiniterecharge2020.PowerCellUserData

class IntakeListener(
        private val worldManager: WorldManager
) : ContactListener {
    override fun endContact(contact: Contact) {
    }

    override fun beginContact(contact: Contact) {
        val a = contact.fixtureA
        val b = contact.fixtureB

        check(a, b) || check(b, a)
    }
    private fun check(intakeFixture: Fixture, powerCellFixture: Fixture): Boolean {
        val intakeData = intakeFixture.userData
        val powerCellData = powerCellFixture.body.userData
        if(intakeData is IntakeUserData && powerCellData is PowerCellUserData){
            val powerCell = powerCellData.powerCell
            worldManager.remove(powerCell)
            return true
        }
        return false
    }

    override fun preSolve(contact: Contact, oldManifold: Manifold) {
    }

    override fun postSolve(contact: Contact, impulse: ContactImpulse) {
    }
}
