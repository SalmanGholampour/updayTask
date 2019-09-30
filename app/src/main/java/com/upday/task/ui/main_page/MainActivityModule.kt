package com.upday.task.ui.main_page

import androidx.recyclerview.widget.LinearLayoutManager
import com.upday.task.data.DataManager
import com.upday.task.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class MainActivityModule {
    @Provides
    internal fun provideMainViewModelForFactory(
        dataManager: DataManager,
        schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable
    ): MainViewModel {
        return MainViewModel(
            dataManager,
            schedulerProvider,
            compositeDisposable
        )
    }


    @Provides
    fun provideImageAdapter(): ImagesAdapter {
        return ImagesAdapter()
    }

    @Provides
    fun provideLinearLayoutManager(activity: MainActivity): LinearLayoutManager {
        return LinearLayoutManager(activity)
    }
}