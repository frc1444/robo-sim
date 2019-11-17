package com.first1444.sim.api.surroundings

interface SurroundingProvider {
    /**
     * Although the returned list has surroundings from a single instant, it's possible that some surroundings were received later
     * than others.
     * @return A list of surroundings from a single instant.
     */
    val surroundings: List<Surrounding>
}
