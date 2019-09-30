package com.upday.task.data.model.auth

import com.google.gson.annotations.SerializedName

data class AuthResponse (
    @SerializedName("access_token")
    var token: String
)