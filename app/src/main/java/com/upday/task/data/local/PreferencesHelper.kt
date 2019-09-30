package com.upday.task.data.local

interface PreferencesHelper {
    fun setAccessToken(accessToken: String)

    fun getAccessToken(): String

}