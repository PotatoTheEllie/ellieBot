package me.ellie.elliebot.command

/**
 * Created by Ellie on 02/06/2017 for SingularProjects.
 * Affiliated with www.minevelop.com
 *
 */
abstract class TwitchCommand(val label:String){

    abstract fun abstractExecute(sender:String, args:List<String>)

}