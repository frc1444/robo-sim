package com.first1444.sim.gdx.sound

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.utils.GdxRuntimeException
import com.first1444.sim.api.sound.Sound
import com.first1444.sim.api.sound.SoundCreator
import com.first1444.sim.api.sound.implementations.SimpleSound

@JvmSynthetic
fun GdxSoundCreator(fileHandleProvider: (String) -> FileHandle): GdxSoundCreator {
    return GdxSoundCreator(object : GdxSoundCreator.FileHandleProvider {
        override fun getFileHandle(string: String): FileHandle = fileHandleProvider(string)
    })
}

class GdxSoundCreator(
        private val fileHandleProvider: FileHandleProvider
) : SoundCreator {
    interface FileHandleProvider {
        fun getFileHandle(string: String): FileHandle
    }
    private val sounds = ArrayList<GdxSound>()
    override fun create(string: String): Sound {
        val sound = try {
            Gdx.audio.newSound(fileHandleProvider.getFileHandle(string))
        } catch(ex: GdxRuntimeException){
            ex.printStackTrace()
            return SimpleSound {
                System.err.println("The requested sound had the following error when it tried to be created:")
                ex.printStackTrace()
                System.err.println("^ End of stack trace.")
            }
        }
        val r = GdxSound(sound)
        sounds.add(r)
        return r
    }

    override fun close() {
        for(sound in sounds){
            sound.close()
        }
    }

}
