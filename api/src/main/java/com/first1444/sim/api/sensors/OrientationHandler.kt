package com.first1444.sim.api.sensors

interface OrientationHandler : AutoCloseable {
    /**
     * @return The [Orientation] instance. This value will not change
     */
    val orientation: Orientation
    val isConnected: Boolean
    val isInitialized: Boolean

    /**
     * Initialize or reinitialize the orientation. May include calibrating, recreating the communication link, etc.
     * @return true if initialization was successful, false otherwise
     */
    fun reinitialize(): Boolean
}
