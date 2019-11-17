package com.first1444.sim.api.selections

class HighestValueSelector<T>(
        private val valueProvider: ValueProvider<T>
) : Selector<T> {
    override fun select(collection: Collection<T>): T {
        require(collection.isNotEmpty()) { "collection cannot be empty" }

        var bestThing: T? = null
        var bestValue: Double? = null
        for(thing in collection){
            val value = valueProvider.getValue(thing)
            if(bestValue == null || value > bestValue){
                bestThing = thing
                bestValue = value
            }
        }
        return bestThing ?: error("bestThing shouldn't be null! collection: $collection")
    }

}
