package com.example.crud_sederhana

import android.content.Context
import android.content.SharedPreferences
import hu.autsoft.krate.Krate
import hu.autsoft.krate.stringPref

class PrefsManager(context: Context) : Krate {

    private val PREFS_IS_LOGIN: String = "prefs_is_login"
    private val PREFS_ID: String = "prefs_is_id"
    private val PREFS_IS_NAME: String = "prefs_is_name"
    private val PREFS_IS_USERNAME: String = "prefs_is_username"
    private val PREFS_IS_EMAIL: String = "prefs_is_email"
    private val PREFS_IS_PASSWORD: String = "prefs_is_password"
    private val PREFS_IS_GENDER: String = "prefs_is_gender"
    private val PREFS_IS_ALAMAT: String = "prefs_is_alamat"

    override val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.applicationContext.getSharedPreferences(
            "notebooks", Context.MODE_PRIVATE
        )
    }

    val prefslogin by stringPref(PREFS_IS_LOGIN, "")
    val prefsID by stringPref(PREFS_ID, "")
    val prefsname by stringPref(PREFS_IS_NAME, "")
    val prefsusername by stringPref(PREFS_IS_USERNAME, "")
    val prefsemail by stringPref(PREFS_IS_EMAIL, "")
    val prefspassword by stringPref(PREFS_IS_PASSWORD, "")
    val prefsgender by stringPref(PREFS_IS_GENDER, "")
    val prefsalamat by stringPref(PREFS_IS_ALAMAT, "")

    fun logOut(){
        sharedPreferences.edit().clear().apply()
    }
}