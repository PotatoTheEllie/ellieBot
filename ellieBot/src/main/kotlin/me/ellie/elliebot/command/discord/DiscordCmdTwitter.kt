package me.ellie.elliebot.command.discord

import me.ellie.elliebot.EllieBot
import me.ellie.elliebot.command.DiscordCommand
import me.ellie.elliebot.util.DiscordConstants
import net.dv8tion.jda.core.Permission
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent

/**
 * Created by Ellie on 11.6.17 for SingularProjects.
 * Affiliated with www.minevelop.com
 *
 */
class DiscordCmdTwitter : DiscordCommand("twitter", Permission.MESSAGE_WRITE, "twitter", 0) {

    override fun abstractExecute(event: GuildMessageReceivedEvent, args: List<String>) =
            EllieBot.instance.discord.jda.getTextChannelById(DiscordConstants.GENERAL).sendMessage("Twitter: @literallyEllie").queue()

}