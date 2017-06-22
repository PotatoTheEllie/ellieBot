package me.ellie.elliebot.command.twitch

import me.ellie.elliebot.EllieBot
import me.ellie.elliebot.util.DateUtil

/**
 * Created by Ellie on 3.6.17 for SingularProjects.
 * Affiliated with her highness
 *
 */
class TwitchCmdUptime : me.ellie.elliebot.command.TwitchCommand("uptime") {

    override fun abstractExecute(sender:String, args: List<String>) = EllieBot.instance.twitch.writeMessage("Uptime " +
            "${DateUtil.formatDateDiff(java.lang.management.ManagementFactory.getRuntimeMXBean().startTime)}.")

}