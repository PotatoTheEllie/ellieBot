package me.ellie.elliebot.thread

import me.ellie.elliebot.EllieBot
import me.ellie.elliebot.log.LogChat
import me.ellie.elliebot.thread.twitch.TwitchMessage
import me.ellie.elliebot.util.DateUtil
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


/**
 * Created by Ellie on 02/06/2017 for SingularProjects.
 * Affiliated with www.minevelop.com
 *
 */
class TwitchHeartbeat(val clientKey:String, val nick:String, val channel:String, val prefix:String): Thread() {

    val instance:EllieBot = EllieBot.instance
    val socket: Socket = Socket("irc.chat.twitch.tv", 6667)
    val out: PrintWriter = PrintWriter(socket.getOutputStream(), true)
    val r:ExecutorService = Executors.newFixedThreadPool(1)
    val announces: MutableList<String> = Arrays.asList("If you're enjoying the stream remember to follow for future notifications! <3",
            "You can join my Discord at https://discord.gg/anAfYBc to join the community!",
            "HypeSquad? Find out more information here https://discordapp.com/hypesquad?ref=qDJpbRg7eh",
            "You can find my schedule in my channel description!",
            "Remember to read the rules!")!!
    val log = LogChat()

    fun init() {
        writeSocket("PASS $clientKey")
        writeSocket("NICK ${nick.toLowerCase()}")
        writeSocket("JOIN #$channel")
        writeMessage("$nick has entered the channel")
        log.write("Init")
    }

    override fun run() {
        val r:BufferedReader = BufferedReader(InputStreamReader(socket.getInputStream()))
        var line: String?

        while(socket.isConnected) {
            line = r.readLine()
            if (line != null) {
                if (line == "PING :tmi.twitch.tv") {
                    writeSocket("PONG :tmi.twitch.tv")
                    continue
                }
                if(line.contains(":tmi.twitch.tv")){
                    println("Received message from server $line")
                    continue
                }
                try {
                    if(line.contains("PRIVMSG")){
                        if(line.contains("$channel :$prefix")) {
                            parseInput(line.split("#${instance.settings.settings["twitchChannel"]}:$prefix")[1], line.split("!")[0].substring(":".length))
                            continue
                        }

                        val message:TwitchMessage = TwitchMessage(line.split(prefix)[0].substring(":".length),
                                DateUtil.getDate(), line.split("#")[1].split(" ")[0], line.substring(line.split(":")[1].length).trim())
                        log.write(message.toString())
                        System.out.println("[Chat] $message")
                    }

                }catch(e:IndexOutOfBoundsException){
                }
                    Thread.sleep(1)
            }
            runAnnouncements()
        }
    }

    fun runAnnouncements(){
        r.submit({
            var seconds:Int = 0
            var index = 0
            // 300
            while (true){
                if (seconds == 300){
                    if(index > announces.size) index = 0
                    writeMessage(announces[index])
                    seconds = 0
                    index++
                }
                seconds++
                Thread.sleep(1000)
            }
        })

    }

    fun writeSocket(message:String){
        out.write(message+"\r\n")
        out.flush()
    }

    fun writeMessage(message:String) = writeSocket("PRIVMSG #$channel :$message")

    fun parseInput(sender:String, input:String){
        if(sender.isEmpty() || input.isEmpty()) return

        val args:List<String> = input.split(" ")
        println("Received command query $input from $sender")
        try {
            instance.twitchCommands[args[0].toLowerCase()]?.abstractExecute(sender, args)
        }catch(e:Exception){
            writeMessage("Error whilst executing command")
        }
    }




}