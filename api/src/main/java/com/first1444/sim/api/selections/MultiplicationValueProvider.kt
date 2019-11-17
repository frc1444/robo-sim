package com.first1444.sim.api.selections

class MultiplicationValueProvider<T>(
        private val valueProviders: Collection<ValueProvider<T>>
) : ValueProvider<T> {
    override fun getValue(thing: T): Double {
        var r = 1.0
        for(provider in valueProviders){
            r *= provider.getValue(thing)
        }
        return r
    }

}
