package me.ellie.elliebot.command.twitch

import me.ellie.elliebot.EllieBot
import me.ellie.elliebot.command.TwitchCommand

/**
 * Created by Ellie on 11.6.17 for SingularProjects.
 * Affiliated with www.minevelop.com
 *
 */
class TwitchCmdTwitter : TwitchCommand("twitter") {

    override fun abstractExecute(sender: String, args: List<String>) = EllieBot.instance.twitch.writeMessage("Twitter: @literallyEllie")


}