package com.first1444.sim.gdx.util

import com.badlogic.gdx.physics.box2d.Contact
import com.badlogic.gdx.physics.box2d.ContactImpulse
import com.badlogic.gdx.physics.box2d.ContactListener
import com.badlogic.gdx.physics.box2d.Manifold

class ContactListenerMultiplexer(
        private val contactListeners: Collection<ContactListener>
) : ContactListener {
    override fun endContact(contact: Contact) {
        for(listener in contactListeners) listener.endContact(contact)
    }

    override fun beginContact(contact: Contact) {
        for(listener in contactListeners) listener.beginContact(contact)
    }

    override fun preSolve(contact: Contact, oldManifold: Manifold) {
        for(listener in contactListeners) listener.preSolve(contact, oldManifold)
    }

    override fun postSolve(contact: Contact, impulse: ContactImpulse) {
        for(listener in contactListeners) listener.postSolve(contact, impulse)
    }

}
