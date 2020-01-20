package com.first1444.sim.gdx.sound

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.utils.GdxRuntimeException
import com.first1444.sim.api.sound.Sound
import com.first1444.sim.api.sound.SoundCreator
import com.first1444.sim.api.sound.implementations.RandomSoundMultiplexer
import com.first1444.sim.api.sound.implementations.SimpleSound

@JvmSynthetic
inline fun GdxSoundCreator(crossinline fileHandleProvider: (String) -> FileHandle): GdxSoundCreator {
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
    private val closeables = ArrayList<AutoCloseable>()
    override fun create(string: String): Sound {
        val fileHandle = fileHandleProvider.getFileHandle(string)
        if(fileHandle.isDirectory){
            val files = fileHandle.list()
            val soundList = ArrayList<Sound>()
            for(file in files){
                try {
                    val gdxSound = Gdx.audio.newSound(file)
                    val sound = GdxSound(gdxSound)
                    closeables.add(sound)
                    soundList.add(sound)
                } catch(ex: GdxRuntimeException){
                    ex.printStackTrace()
                }
            }
            if(soundList.isEmpty()){
                return SimpleSound {
                    System.err.println("Directory=$string fileHandle=$fileHandle didn't have any sounds in it!")
                }
            }
            return RandomSoundMultiplexer(soundList)
        }
        val sound = try {
            Gdx.audio.newSound(fileHandle)
        } catch(ex: GdxRuntimeException){
            ex.printStackTrace()
            return SimpleSound {
                System.err.println("The requested sound ($string) had the following error when it tried to be created:")
                ex.printStackTrace()
                System.err.println("^ End of stack trace.")
            }
        }
        val r = GdxSound(sound)
        closeables.add(r)
        return r
    }

    @Throws(Exception::class)
    override fun close() {
        for(closeable in closeables){
            closeable.close()
        }
    }

}
