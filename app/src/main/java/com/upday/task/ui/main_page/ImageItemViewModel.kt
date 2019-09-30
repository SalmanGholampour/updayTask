package com.upday.task.ui.main_page

import androidx.databinding.ObservableField
import com.upday.task.data.model.image.ImageData

class ImageItemViewModel(imageData: ImageData) {
    var url:ObservableField<String> = ObservableField()
    var ratio:ObservableField<String> = ObservableField()
    init {
        url.set(imageData.assets.imagePreview.url)
        ratio.set(imageData.aspect.toString())
    }

}