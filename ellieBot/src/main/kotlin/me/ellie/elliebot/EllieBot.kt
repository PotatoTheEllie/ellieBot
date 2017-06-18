package me.ellie.elliebot

import me.ellie.elliebot.command.DiscordCommand
import me.ellie.elliebot.command.TwitchCommand
import me.ellie.elliebot.file.JsonBotConfig
import me.ellie.elliebot.thread.DiscordHeartbeat
import me.ellie.elliebot.thread.TwitchHeartbeat
import org.reflections.Reflections
import java.util.*


/**
 * Created by Ellie on 02/06/2017 for SingularProjects.
 * Affiliated with www.minevelop.com
 *
 */

class EllieBot private constructor(){

    lateinit var heart : TwitchHeartbeat
    lateinit var discord: DiscordHeartbeat
    lateinit var settings: JsonBotConfig

    val twitchCommands: HashMap<String, TwitchCommand> = HashMap()
    val discordCommands: HashMap<String, DiscordCommand> = HashMap()

    fun init() {
        val start:Long = System.currentTimeMillis()
        logInfo("Loading EllieBot for twitch/discord")

        settings = JsonBotConfig()

        val ref:Reflections = Reflections("me.ellie.elliebot.command.twitch")
        ref.getSubTypesOf(TwitchCommand::class.java).forEach { subclass ->
            val command = subclass.newInstance()
            twitchCommands.put(command.label.toLowerCase(), command)
        }
        logInfo("Loaded ${twitchCommands.size} Twitch commands.")

        val cmd:Reflections = Reflections("me.ellie.elliebot.command.discord")
        cmd.getSubTypesOf(DiscordCommand::class.java).forEach { subclass ->
            val command = subclass.newInstance()
            discordCommands.put(command.label.toLowerCase(), command)
        }
        logInfo("Loaded ${discordCommands.size} Discord commands.")

        //
        heart = TwitchHeartbeat(settings.settings["twitchToken"] as String, "literallybootie", settings.settings["twitchChannel"] as String)
        heart.init()
        heart.start()

        //
        discord = DiscordHeartbeat(settings.settings["discordToken"] as String)
        discord.init()
        discord.start()

        logInfo("Loaded in "+(System.currentTimeMillis() - start)+"ms")
    }

    private object container {
        val instance = EllieBot()
    }

    companion object {
        val instance: EllieBot by lazy {
            container.instance
        }
    }

    fun shutdown(status:Int=0){
        twitchCommands.clear()
        discordCommands.clear()

        heart.out.close()
        heart.socket.close()
        heart.announces.clear()
        heart.r.shutdownNow()
        heart.join()

        discord.jda.shutdown(true)
        discord.join()

        System.exit(status)
    }

    fun logInfo(info:String) = println("[INFO] $info")
    fun logWarn(warn:String) = println("[WARN] $warn")
    fun logError(error:String) = println("[ERROR] $error")

}


fun main(args: Array<String>) {
    EllieBot.instance.init()
}