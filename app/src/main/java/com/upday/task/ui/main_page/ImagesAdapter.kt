package com.upday.task.ui.main_page

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.upday.task.data.model.image.ImageData
import com.upday.task.databinding.ItemImageViewBinding

class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {
    private  var imageData = mutableListOf<ImageData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding: ItemImageViewBinding =
            ItemImageViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  imageData.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(imageData[position])
    }

    fun addItemsToList(data: MutableList<ImageData>?) {
        data?.let {
            imageData=data
            notifyDataSetChanged()
        }

    }


    class ImageViewHolder(private val binding: ItemImageViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(imageData: ImageData) {
            val viewModel = ImageItemViewModel(imageData)
            binding.viewModel = viewModel
            binding.executePendingBindings()


        }

    }
}