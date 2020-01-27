package com.first1444.sim.gdx

class UpdateableMultiplexer(
        private val updateableList: List<Updateable>
) : Updateable {

    override fun update(delta: Float) {
        for(updateable in updateableList){
            updateable.update(delta)
        }
    }
    @Throws(Exception::class)
    override fun close() {
        var exception: Exception? = null
        for(updateable in updateableList){
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
