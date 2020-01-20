package com.first1444.sim.api.selections

interface ValueProvider<in T> {
    fun getValue(thing: T): Double
    companion object {
        @JvmSynthetic
        inline operator fun <T>invoke(crossinline lambda: (T) -> Double): ValueProvider<T> = object : ValueProvider<T> {
            override fun getValue(thing: T): Double = lambda(thing)
        }
    }
}
