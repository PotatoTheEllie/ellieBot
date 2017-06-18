package me.ellie.elliebot.thread.twitch

/**
 * Created by Ellie on 11.6.17 for SingularProjects.
 * Affiliated with www.minevelop.com
 *
 */
class TwitchMessage(val sender:String, val timeStamp:String, val channel:String, val content:String){


    override fun toString(): String {
        return "[#$channel] $sender: $content"
    }

}
