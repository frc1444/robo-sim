package com.first1444.sim.gdx

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener

object ScreenSelector {
    @JvmStatic
    fun populateTable(table: Table, uiSkin: Skin, selections: Collection<ScreenSelect>){

        for(selection in selections) {
            table.add(TextButton(selection.label, uiSkin).apply {
                addListener(object : ClickListener() {
                    override fun clicked(event: InputEvent, x: Float, y: Float) {
                        if(event.type == InputEvent.Type.touchUp){
                            selection.onClick.run()
                        }
                    }
                })
            })
            table.row()
        }
    }
    class ScreenSelect(
            val label: String,
            val onClick: Runnable
    ) {
        constructor(label: String, onClick: () -> Unit) : this(label, Runnable(onClick))
    }
}
