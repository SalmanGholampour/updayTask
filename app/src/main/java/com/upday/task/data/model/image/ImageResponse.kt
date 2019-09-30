package com.upday.task.data.model.image

import com.google.gson.annotations.SerializedName

data class ImageResponse (
    @SerializedName("data")
    var data: List<ImageData>
)