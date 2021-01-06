package com.first1444.sim.api.selections

fun interface ValueProvider<in T> {
    fun getValue(thing: T): Double
}
