package me.ellie.elliebot.command.twitch

import me.ellie.elliebot.EllieBot

/**
 * Created by Ellie on 02/06/2017 for SingularProjects.
 * Affiliated with www.minevelop.com
 *
 */
class TwitchCmdPing : me.ellie.elliebot.command.TwitchCommand("ping"){

    override fun abstractExecute(sender:String, args: List<String>) = me.ellie.elliebot.EllieBot.Companion.instance.twitch.writeMessage("Pong!")

}