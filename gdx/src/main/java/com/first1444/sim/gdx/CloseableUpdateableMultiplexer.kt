package com.first1444.sim.gdx

class CloseableUpdateableMultiplexer(
        private val closeableUpdateableList: List<CloseableUpdateable>
) : CloseableUpdateable {

    override fun update(delta: Float) {
        for(updateable in closeableUpdateableList){
            updateable.update(delta)
        }
    }
    @Throws(Exception::class)
    override fun close() {
        var exception: Exception? = null
        for(updateable in closeableUpdateableList){
            try {
                updateable.close()
            } catch(e: Exception){
                if(exception == null){
                    exception = e
                }
            }
        }
        if(exception != null){
            throw exception
        }
    }

}
