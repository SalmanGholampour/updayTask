package com.upday.task.ui.main_page

import androidx.databinding.ObservableArrayList
import com.upday.task.data.DataManager
import com.upday.task.base.BaseViewModel
import com.upday.task.data.model.image.ImageData
import com.upday.task.data.model.image.ImageRequest
import com.upday.task.ui.error.GlobalErrorHandler
import com.upday.task.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BaseViewModel<MainNavigator>(dataManager, schedulerProvider, compositeDisposable) {
    var imageDataObservableArrayList = ObservableArrayList<ImageData>()
    private var pageNumber = 1

    companion object {
        const val perPage = 20
    }

    fun fetchImages(imageRequest: ImageRequest) {

        compositeDisposable.add(
            dataManager.getImages(imageRequest).subscribeOn(schedulerProvider.io()).observeOn(
                schedulerProvider.ui()
            ).subscribe({
                imageDataObservableArrayList.addAll(it.data)
                pageNumber++
            }) {
                getNavigator()?.let { it1 -> GlobalErrorHandler.handle(it, it1) }
            })
    }

    fun isAccessTokenExist(): Boolean {

        return dataManager.getAccessToken().isNotEmpty()

    }

    fun getImageRequest(): ImageRequest {
        return ImageRequest("", perPage, pageNumber)
    }
}