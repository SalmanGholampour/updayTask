package com.upday.task.utils

import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.upday.task.data.model.image.ImageData
import com.upday.task.ui.main_page.ImagesAdapter

@BindingAdapter("adapter")
fun addImagesItem(view: RecyclerView, data: List<ImageData>) {
    view.adapter?.let {
        val adapter: ImagesAdapter = view.adapter as ImagesAdapter
        adapter.addItemsToList(data.toMutableList())
    }
}

@BindingAdapter("ratio")
fun setImageViewRatio(view: ImageView, ratio: String) {
    (view.layoutParams as ConstraintLayout.LayoutParams).dimensionRatio = "$ratio:1"

}

@BindingAdapter("content")
fun loadImage(view: ImageView, url: String) {
    Picasso.get().load(url).into(view)

}