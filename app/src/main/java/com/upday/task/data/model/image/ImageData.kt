package com.upday.task.data.model.image

import com.google.gson.annotations.SerializedName

data class ImageData(
    @SerializedName("aspect")
    var aspect: Float,
    @SerializedName("id")
    var id: String,
    @SerializedName("assets")
    var assets: Asset
)