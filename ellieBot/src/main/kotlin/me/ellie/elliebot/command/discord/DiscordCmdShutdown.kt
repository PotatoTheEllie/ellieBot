package me.ellie.elliebot.command.discord

import me.ellie.elliebot.EllieBot
import me.ellie.elliebot.command.DiscordCommand
import net.dv8tion.jda.core.Permission
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent

/**
 * Created by Ellie on 8.6.17 for SingularProjects.
 * Affiliated with www.minevelop.com
 *
 */
class DiscordCmdShutdown : DiscordCommand("shutdown", Permission.ADMINISTRATOR, "shutdown", 0){

    override fun abstractExecute(event: GuildMessageReceivedEvent, args: List<String>) = EllieBot.instance.shutdown(0)


}