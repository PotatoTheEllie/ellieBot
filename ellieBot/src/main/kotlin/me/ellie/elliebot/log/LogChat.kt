package me.ellie.elliebot.log

import me.ellie.elliebot.util.DateUtil
import java.io.BufferedWriter
import java.io.File

/**
 * Created by Ellie on 11.6.17 for SingularProjects.
 * Affiliated with www.minevelop.com
 *
 */
class LogChat {

    private val directory:File = File("logs")
    private var currentFile:File
    private var writer:BufferedWriter

    init {
        if(!directory.isDirectory) directory.mkdir()
        currentFile = File(directory, "chat_log"+directory.listFiles().size+".txt")
        currentFile.createNewFile()
        writer = BufferedWriter(currentFile.bufferedWriter())
    }

    fun write(message:String){
        writer.write("[${DateUtil.getDate()}] $message")
        writer.flush()
        writer.newLine()
    }

}