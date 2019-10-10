package com.first1444.sim.api.event

interface EventHandler {
    fun canHandleEvent(event: Event): Boolean
    fun handleEvent(event: Event, data: Any?): Boolean
    /**
     * @return A [Set] of [Event]s that can be handled. The returned value of this should not change throughout this object's lifetime.
     */
    val events: Set<Event>

    companion object {
        @JvmField
        val DO_NOTHING = object : EventHandler {
            override fun canHandleEvent(event: Event): Boolean = false
            override fun handleEvent(event: Event, data: Any?): Boolean = false
            override val events: Set<Event> = emptySet()
        }
    }
}
