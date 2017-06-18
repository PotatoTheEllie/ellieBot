package me.ellie.elliebot.util

import java.awt.Color

/**
 * Created by Ellie on 8.6.17 for SingularProjects.
 * Affiliated with www.minevelop.com
 *
 */
object DiscordConstants {

    const val SELF:Long = 322089362722521100
    const val GENERAL:Long = 322276536407556117
    const val STREAM:Long = 322316465346838528

    fun getEmbedBuilder(): EmbedUtil {
        val embedBuilder:EmbedUtil = EmbedUtil()
        embedBuilder.setColor(Color(0, 198, 255))
        embedBuilder.setAuthor("literallyBot", "https://discordapp.com", null)
        return embedBuilder
    }

}