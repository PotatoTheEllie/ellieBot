package me.ellie.elliebot.command.twitch

import me.ellie.elliebot.EllieBot
import me.ellie.elliebot.command.TwitchCommand
import me.ellie.elliebot.util.TwitchConstants

/**
 * Created by Ellie on 02/06/2017 for SingularProjects.
 * Affiliated with www.minevelop.com
 *
 */
class TwitchCmdShutdown : TwitchCommand("shutdown") {

    override fun abstractExecute(sender:String, args: List<String>) {
        if (sender == TwitchConstants.ELLIE) EllieBot.instance.shutdown()
    }

}