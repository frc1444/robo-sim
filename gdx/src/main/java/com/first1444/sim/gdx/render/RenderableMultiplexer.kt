package com.first1444.sim.gdx.render

import com.first1444.sim.gdx.Renderable

class RenderableMultiplexer(
        private val list: List<Renderable>
) : Renderable{

    override fun render(delta: Float) {
        for(renderable in list){
            renderable.render(delta)
        }
    }
    override fun resize(width: Int, height: Int) {
        for(renderable in list){
            renderable.resize(width, height)
        }
    }

    override fun dispose() {
        for(renderable in list){
            renderable.dispose()
        }
    }

    class Builder {
        private val list: MutableList<Renderable> = ArrayList()

        fun add(renderable: Renderable): Builder {
            list.add(renderable)
            return this
        }

        fun build(): RenderableMultiplexer{
            return RenderableMultiplexer(ArrayList(list))
        }

    }

}
