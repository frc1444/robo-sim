package com.first1444.sim.api.sensors

/**
 * A [OrientationHandler] that assumes [orientation] is always working perfectly.
 */
class DefaultOrientationHandler(
        override val orientation: Orientation
) : OrientationHandler {
    override val isConnected: Boolean
        get() = true
    override val isInitialized: Boolean
        get() = true

    override fun reinitialize() = true
    override fun close() {}

}
