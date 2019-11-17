package com.first1444.sim.api.selections

class HighestValueSelector<T>
@JvmOverloads constructor(
        private val valueProvider: ValueProvider<T>,
        private val valueAllower: ValueAllower<T> = ValueAllower.createAlwaysAllow()
) : Selector<T> {
    override fun select(collection: Collection<T>): T? {

        var bestThing: T? = null
        var bestValue: Double? = null
        for(thing in collection){
            val value = valueProvider.getValue(thing)
            if(bestValue == null || value > bestValue){
                bestThing = thing
                bestValue = value
            }
        }
        bestThing ?: return null
        bestValue!!
        if(!valueAllower.isAllowed(bestThing, bestValue)){
            return null
        }
        return bestThing
    }

}
