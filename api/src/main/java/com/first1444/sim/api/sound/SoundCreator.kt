package com.first1444.sim.api.sound

interface SoundCreator : AutoCloseable {
    fun create(string: String): Sound

    /**
     * Calls disposes any necessary resources on sounds created using [create]. Also closes any other resources that were created with this object
     */
    @Throws(Exception::class)
    override fun close()

    companion object {
        inline fun createWithoutClose(crossinline lambda: (String) -> Sound) = object : SoundCreator {
            override fun create(string: String) = lambda(string)
            override fun close() {
            }
        }
    }
}
