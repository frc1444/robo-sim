package com.first1444.sim.api.selections

interface Selector<T> {
    /**
     * @param collection A collection with values of [T]. This may be empty.
     * @return One [T] from [collection] or null
     */
    fun select(collection: Collection<T>): T?

    companion object {
        @JvmSynthetic
        operator fun <T>invoke(lambda: (Collection<T>) -> T?): Selector<T> = object : Selector<T> {
            override fun select(collection: Collection<T>): T? = lambda(collection)
        }
    }
}
