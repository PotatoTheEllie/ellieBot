package me.ellie.elliebot.command.twitch

import me.ellie.elliebot.EllieBot

/**
 * Created by Ellie on 3.6.17 for SingularProjects.
 * Affiliated with her highness
 *
 */
class TwitchCmdUptime : me.ellie.elliebot.command.TwitchCommand("uptime") {

    override fun abstractExecute(sender:String, args: List<String>) = me.ellie.elliebot.EllieBot.Companion.instance.heart.writeMessage("Uptime ${me.ellie.elliebot.util.DateUtil.formatDateDiff(java.lang.management.ManagementFactory.getRuntimeMXBean().startTime)}.")

}