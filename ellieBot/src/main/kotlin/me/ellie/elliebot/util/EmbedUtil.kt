package me.ellie.elliebot.util

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.MessageEmbed
import java.lang.reflect.Field
import java.util.*


/**
 * Created by Ellie on 7.6.17 for SingularProjects.
 * Affiliated with www.minevelop.com
 *
 */
class EmbedUtil : EmbedBuilder() {

    private lateinit var fieldsField: Field
    private lateinit var innfields: MutableList<MessageEmbed.Field>

    /**
     * The constructor, which will set things up, and send a stacktrace is an error occurs.
     */
    fun EmbedUtil() {
        try {
            fieldsField = EmbedBuilder::class.java.getDeclaredField("fields")
            fieldsField.isAccessible = true
            pullUpdate()
        } catch (exception: NoSuchFieldException) {
            throw IllegalStateException("The embed builder caused an error, hence it could not be initialised.")
        }

    }

    /**
     * Removes a field from the EmbedBuilder.
     * @param title The name of the field you wish to remove.
     * *
     * @return The UtilsEmbedBuilder instance.
     */
    fun removeField(title: String): EmbedUtil {
        pullUpdate()
        val toRemove = ArrayList<MessageEmbed.Field>()
        innfields.stream().filter({ fStream -> fStream.name.equals(title, ignoreCase = true) }).forEach({ toRemove.add(it) })
        innfields.removeAll(toRemove)
        pushUpdate()
        return this
    }

    /**
     * Changes the value of a field from the EmbedBuilder.
     * @param title The name of the field you wish to change the value of.
     * *
     * @param value The new value of the field.
     * *
     * @return The UtilsEmbedBuilder instance.
     */
    fun setFieldValue(title: String, value: String): EmbedUtil {
        var value = value
        if (value.length > 1024) {
            value = value.substring(value.length - 6)
            value += "[...]"
        }
        val finalValue = value
        pullUpdate()
        innfields.stream().filter { fStream -> fStream.name.equals(title, ignoreCase = true) }
                .forEach { fStream ->
                    try {
                        val fStreamField = fStream.javaClass.getDeclaredField("value")
                        fStreamField.isAccessible = true
                        fStreamField.set(fStream, finalValue)
                    } catch (exception: NoSuchFieldException) {
                        exception.printStackTrace()
                    } catch (exception: IllegalAccessException) {
                        exception.printStackTrace()
                    }
                }
        return this
    }

    /**
     * Replaces a string in the value of a field from the EmbedBuilder.
     * @param title The name of the field you wish to malipulate.
     * *
     * @param from The string it will replace.
     * *
     * @param to The string it will replace to.
     * *
     * @return The UtilsEmbedBuilder instance.
     */
    fun replaceFieldValue(title: String, from: String, to: String): EmbedUtil {
        pullUpdate()
        innfields!!.stream().filter { fStream -> fStream.name.equals(title, ignoreCase = true) }
                .forEach { fStream ->
                    try {
                        val fStreamField = fStream.javaClass.getDeclaredField("value")
                        fStreamField.isAccessible = true
                        val value = fStreamField.get(fStream) as String
                        var checkValue = value.replace(from, to)
                        if (checkValue.length > 1024) {
                            checkValue = checkValue.substring(value.length - 6)
                            checkValue += "[...]"
                        }
                        val finalValue = checkValue
                        fStreamField.set(fStream, finalValue)
                    } catch (exception: NoSuchFieldException) {
                        exception.printStackTrace()
                    } catch (exception: IllegalAccessException) {
                        exception.printStackTrace()
                    }
                }
        return this
    }

    /**
     * Changes whether a field should be displayed inline from the EmbedBuilder.
     * @param title The name fo the field you wish to change the inline property of.
     * *
     * @param inline The new inline value.
     * *
     * @return The UtilsEmbedBuilder instance
     */
    fun setFieldInline(title: String, inline: Boolean): EmbedUtil {
        pullUpdate()
        innfields!!.stream().filter { fStream -> fStream.name.equals(title, ignoreCase = true) }
                .forEach { fStream ->
                    try {
                        val fStreamField = fStream.javaClass.getDeclaredField("inline")
                        fStreamField.isAccessible = true
                        fStreamField.set(fStream, inline)
                    } catch (exception: NoSuchFieldException) {
                        exception.printStackTrace()
                    } catch (exception: IllegalAccessException) {
                        exception.printStackTrace()
                    }
                }
        return this
    }

    /**
     * This method updates the local MessageEmbed.Field list.
     */
    private fun pullUpdate() {
        try {
            innfields = fieldsField.get(this) as MutableList<MessageEmbed.Field>
        } catch (exception: IllegalAccessException) {
            exception.printStackTrace()
        }

    }

    /**
     * This method updates the EmbedBuilder MessageEmbed.Field list.
     */
    private fun pushUpdate() {
        try {
            fieldsField.set(this, innfields)
        } catch (exception: IllegalAccessException) {
            exception.printStackTrace()
        }

    }

}