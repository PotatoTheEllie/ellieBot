package me.ellie.elliebot.file

import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.*

/**
 * Created by Ellie on 18.6.17 for SingularProjects.
 * Affiliated with www.minevelop.com
 *
 */
class JsonBotConfig {

    val file:File = File("bot.json")
    val settings:HashMap<String, String> = HashMap()

    init {

        if(!file.exists() && file.createNewFile()){
            val jsonConfig = "{\n" +
                    "\t\"botPrefix\":\"!\",\n" +

                    "\t\"discordToken\":\"none\",\n" +
                    "\t\"discordGameText\":\"having a gud time\",\n" +
                    "\t\"discordGameUrl\":\"https://twitch.tv/literallyellie\",\n" +
                    "\t\"discordState\":\"online\",\n" +

                    "\t\"twitchToken\":\"none\",\n" +
                    "\t\"twitchChannel\":\"literallyellie\",\n" +
                    "}"
            val writer = BufferedWriter(FileWriter(file))
            writer.write(jsonConfig)
            writer.close()
        }


        val jsonParser = JSONParser()
        val reader = BufferedReader(FileReader(file))
        val jsonObject = jsonParser.parse(reader) as JSONObject
        jsonObject.toMap().forEach({ t, u ->  settings.put(t.toString(), u.toString())})
        reader.close()
    }

}