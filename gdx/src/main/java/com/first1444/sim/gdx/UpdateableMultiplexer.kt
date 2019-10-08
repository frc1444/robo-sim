package com.first1444.sim.gdx

/**
 * A class that updates a list of [Updateable]s.
 *
 * @param updateableList The list of [Updateable]s. If this changes, it will update whatever is in this list.
 */
class UpdateableMultiplexer(
        private val updateableList: List<Updateable>
) : Updateable {
    override fun update(delta: Float) {
        for(updateable in updateableList){
            updateable.update(delta)
        }
    }

}
