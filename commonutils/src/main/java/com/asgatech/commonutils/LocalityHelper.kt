package com.asgatech.commonutils

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import androidx.annotation.RequiresApi
import java.util.*

/**
 * Handle the language of the app
 */
class LocalityHelper {
    /**
     * Use for setting the new resources context for the app inside the super fun of [attachBaseContext]
     */
    fun onAttach(context: Context, lang: String): Context {
        return setLocality(context, lang)
    }

    /**
     * Set default language of the app
     */
    fun setLocality(context: Context, lang: String): Context {
        val res = context.resources
        val configuration = res.configuration
        val locale = Locale(lang)
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
                updateConfigurationForAboveN(locale, configuration)
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                updateConfigurationForBelowN(configuration, locale)
            }
            else -> {
                @Suppress("DEPRECATION")
                configuration.locale = locale
            }
        }
        return context.createConfigurationContext(configuration)
    }

    /**
     * Set locale configuration for above Android Nougat
     */
    @RequiresApi(Build.VERSION_CODES.N)
    private fun updateConfigurationForAboveN(
        locale: Locale,
        configuration: Configuration
    ) {
        val localeList = LocaleList(locale)
        LocaleList.setDefault(localeList)
        configuration.setLocales(localeList)
        configuration.setLocale(locale)
    }

    /**
     * Set locale configuration for below Android Nougat
     */
    private fun updateConfigurationForBelowN(
        configuration: Configuration,
        locale: Locale
    ) {
        configuration.setLocale(locale)
    }

    /**
     * Read string from strings file by its language key
     */
    fun readStringByLocale(context: Context, resId: Int): String {
        val conf = context.resources.configuration
        val resources = context.createConfigurationContext(conf)
        return resources.getString(resId)
    }

    /**
     * Read string array from strings file by its language key and return it as Array of String
     */
    fun readArrayStringByLocale(context: Context, resId: Int): Array<String?> {
        val conf = context.resources.configuration
        val resources = context.createConfigurationContext(conf)
        return resources.resources.getStringArray(
            resId
        )
    }
}