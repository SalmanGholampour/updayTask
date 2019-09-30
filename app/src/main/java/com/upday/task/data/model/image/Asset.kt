package com.upday.task.data.model.image

import com.google.gson.annotations.SerializedName

data class Asset(
    @SerializedName("preview")
    var imagePreview: ImagePreview,

    @SerializedName("large_thumb")
    var largeThumb: LargeThumb,

    @SerializedName("small_thumb")
    var smallThumb: SmallThumb,

    @SerializedName("huge_thumb")
    var hugeThumb: HugeThumb,

    @SerializedName("preview_1000")
    var preview1000: Preview_1000,

    @SerializedName("preview_1500")
    var preview1500: Preview_1500

)