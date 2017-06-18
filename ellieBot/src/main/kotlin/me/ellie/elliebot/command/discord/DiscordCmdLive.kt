package me.ellie.elliebot.command.discord

import me.ellie.elliebot.command.DiscordCommand
import me.ellie.elliebot.util.DiscordConstants
import net.dv8tion.jda.core.Permission
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent

/**
 * Created by Ellie on 8.6.17 for SingularProjects.
 * Affiliated with www.minevelop.com
 *
 */
class DiscordCmdLive : DiscordCommand("live", Permission.ADMINISTRATOR, "", 0) {

    override fun abstractExecute(event: GuildMessageReceivedEvent, args: List<String>) {
        message("Announcing...", event.channel)
        event.jda.getTextChannelById(DiscordConstants.STREAM).sendMessage("@everyone Live footage of Ellie streaming at https://www.twitch.tv/literallyellie ! Go over now to check it out! :D").queue()
    }


}