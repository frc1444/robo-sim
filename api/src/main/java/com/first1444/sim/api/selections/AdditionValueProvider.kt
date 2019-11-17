package com.first1444.sim.api.selections

class AdditionValueProvider<T>
@JvmOverloads constructor(
        private val valueProviders: Collection<ValueProvider<T>>,
        private val startingValue: Double = 0.0
) : ValueProvider<T> {
    override fun getValue(thing: T): Double {
        var r = startingValue
        for(provider in valueProviders){
            r += provider.getValue(thing)
        }
        return r
    }

}
