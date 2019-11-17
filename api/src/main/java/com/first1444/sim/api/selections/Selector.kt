package com.first1444.sim.api.selections

interface Selector<T> {
    /**
     * @param collection A non-empty collection that contains values of [T]
     * @return One [T] from [collection]
     */
    fun select(collection: Collection<T>): T

    companion object {
        @JvmSynthetic
        operator fun <T>invoke(lambda: (Collection<T>) -> T): Selector<T> = object : Selector<T> {
            override fun select(collection: Collection<T>): T = lambda(collection)
        }
    }
}
