package com.first1444.sim.api.selections

interface ValueProvider<in T> {
    fun getValue(thing: T): Double
    companion object {
        @JvmSynthetic
        operator fun <T>invoke(lambda: (T) -> Double): ValueProvider<T> = object : ValueProvider<T> {
            override fun getValue(thing: T): Double = lambda(thing)
        }
    }
}
