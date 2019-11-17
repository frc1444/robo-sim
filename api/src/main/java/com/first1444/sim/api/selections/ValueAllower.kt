package com.first1444.sim.api.selections

interface ValueAllower<in T> {
    fun isAllowed(thing: T, value: Double): Boolean
    companion object {
        @JvmStatic
        fun <T>createAlwaysAllow() = object : ValueAllower<T> {
            override fun isAllowed(thing: T, value: Double): Boolean = true
        }
        @JvmSynthetic
        operator fun <T>invoke(lambda: (T, Double) -> Boolean): ValueAllower<T> = object : ValueAllower<T> {
            override fun isAllowed(thing: T, value: Double): Boolean = lambda(thing, value)
        }
    }
}
