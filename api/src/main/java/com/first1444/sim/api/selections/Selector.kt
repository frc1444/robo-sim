package com.first1444.sim.api.selections

fun interface Selector<T> {
    /**
     * @param collection A collection with values of [T]. This may be empty.
     * @return One [T] from [collection] or null
     */
    fun select(collection: Collection<T>): T?

}
