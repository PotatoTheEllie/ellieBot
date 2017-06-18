package me.ellie.elliebot.command

import net.dv8tion.jda.core.Permission
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.TextChannel
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent

/**
 * Created by Ellie on 02/06/2017 for SingularProjects.
 * Affiliated with www.minevelop.com
 *
 */
abstract class DiscordCommand(val label:String, val permission: Permission = Permission.MESSAGE_WRITE, val syntax:String, val minArgs:Int){

    abstract fun abstractExecute(event:GuildMessageReceivedEvent, args:List<String>)

    fun execute(event:GuildMessageReceivedEvent, args:List<String>){
        val sender:Member = event.member
        val channel:TextChannel = event.channel

        if(!sender.hasPermission(permission)){
            message("[${label.capitalize()}] No permission ${sender.nickname}.", channel)
            return
        }

        if(args.size < minArgs){
            message("[${label.capitalize()}] Correct usage: $syntax, ${sender.nickname}", channel)
            return
        }
        abstractExecute(event, args)
    }

    fun message(message:String, channel:TextChannel){
        channel.sendMessage("[${label.capitalize()}] $message").queue()
    }

}