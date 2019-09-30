package com.upday.task.data.model.image

import com.google.gson.annotations.SerializedName

data class Preview_1500(
    @SerializedName("height")
    var height: Int,
    @SerializedName("width")
    var width: Int,
    @SerializedName("url")
    var url: String

)