package me.ellie.elliebot.command.discord

import me.ellie.elliebot.EllieBot
import me.ellie.elliebot.command.DiscordCommand
import me.ellie.elliebot.util.DiscordConstants
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.Permission
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent

/**
 * Created by Ellie on 7.6.17 for SingularProjects.
 * Affiliated with www.minevelop.com
 *
 */
class DiscordCmdHelp : DiscordCommand("help", Permission.MESSAGE_WRITE, "help", 0){

    val embed:EmbedBuilder

    init {
        embed = DiscordConstants.getEmbedBuilder()
    }

    override fun abstractExecute(event: GuildMessageReceivedEvent, args: List<String>) {
        EllieBot.instance.discordCommands.values
                .filter { it.permission == Permission.MESSAGE_WRITE }
                .forEach { embed.addField("", "", false) }

    }

}