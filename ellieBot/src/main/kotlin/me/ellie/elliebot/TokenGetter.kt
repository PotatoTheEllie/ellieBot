package me.ellie.elliebot

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by Ellie on 02/06/2017 for SingularProjects.
 * Affiliated with www.minevelop.com
 *
 */
class TokenGetter(clientId:String) {
    val url:URL = URL("https://api.twitch.tv/kraken/oauth2/authenticate?client_id=$clientId&redirect_uri=http://localhost&response_type=token&scope=chat_login")
    var token: String = ""

    fun init(): String {

        val conn:HttpURLConnection = url.openConnection() as HttpURLConnection
        conn.readTimeout = 5000
        conn.instanceFollowRedirects = false
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
        val status:Int = conn.responseCode


        var redirect = false
        if (status != HttpURLConnection.HTTP_OK) {
            if (status == HttpURLConnection.HTTP_MOVED_TEMP
                    || status == HttpURLConnection.HTTP_MOVED_PERM
                    || status == HttpURLConnection.HTTP_SEE_OTHER)
                redirect = true
        }
        conn.disconnect()
        if(redirect){

            val newUrl:String = conn.getHeaderField("Location")

            println("Waiting on user response at $newUrl Input token when ready")
            val reader:BufferedReader = BufferedReader(InputStreamReader(System.`in`))
            while (token.isEmpty()){
                val t = reader.readLine()
                if(t != null && t.isNotEmpty()){
                    token = t
                    break
                }
                Thread.sleep(1)
            }
        }

        return token
    }




}
