package com.upday.task.data.model.image

import com.google.gson.annotations.SerializedName

data class HugeThumb(
    @SerializedName("height")
    var height: Int,
    @SerializedName("width")
    var width: Int,
    @SerializedName("url")
    var url: String

)