package com.first1444.sim.api.selections

fun interface ValueAllower<in T> {
    fun isAllowed(thing: T, value: Double): Boolean
    companion object {
        @JvmStatic
        fun <T>createAlwaysAllow() = ValueAllower<T> { _, _ -> true }
    }
}
