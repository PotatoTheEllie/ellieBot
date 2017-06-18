package me.ellie.elliebot.thread

import me.ellie.elliebot.EllieBot
import me.ellie.elliebot.util.DiscordConstants
import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.entities.Game
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter

/**
 * Created by Ellie on 7.6.17 for SingularProjects.
 * Affiliated with www.minevelop.com
 *
 */
class DiscordHeartbeat(val loginToken:String): Thread() {

    lateinit var jda: JDA
    val instance:EllieBot = EllieBot.instance

    fun init() {
        jda = JDABuilder(AccountType.BOT)
                .addEventListener(object : ListenerAdapter() {

                    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent?) {
                        val member: Member = event!!.member
                        val message: String = event.message.rawContent

                        if (member.user.idLong == DiscordConstants.SELF || !message.startsWith("!")) return

                        val msg: String = message.substring("!".length)
                        instance.discordCommands[msg.toLowerCase()]?.execute(event, msg.split(" ".toRegex()))
                    }

                    override fun onGuildMemberJoin(event: GuildMemberJoinEvent?) {
                        jda.getTextChannelById(DiscordConstants.GENERAL).sendMessage("Heyo <@${event!!.member.user.idLong}>. Welcome Ellie's Discord!").queue()
                    }

                    override fun onGuildMemberLeave(event: GuildMemberLeaveEvent?) {
                        jda.getTextChannelById(DiscordConstants.GENERAL).sendMessage("${event!!.member.nickname} left").queue()
                    }

                })
                .setGame(Game.of(instance.settings.settings["discordGameText"] as String, instance.settings.settings["discordGameUrl"] as String))
                .setToken(loginToken).buildAsync()

    }





}