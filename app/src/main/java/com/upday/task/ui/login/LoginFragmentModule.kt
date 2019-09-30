package com.upday.task.ui.login

import com.upday.task.data.DataManager
import com.upday.task.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class LoginFragmentModule {
    @Provides
    fun provideLoginViewModelForFactory(
        dataManager: DataManager,
        schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable
    ): LoginViewModel {
        return LoginViewModel(dataManager, schedulerProvider, compositeDisposable)
    }


}