package com.upday.task.base

import androidx.lifecycle.ViewModel
import com.upday.task.data.DataManager
import com.upday.task.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BaseViewModel<N>(
    val dataManager: DataManager,
    val schedulerProvider: SchedulerProvider,
    val compositeDisposable: CompositeDisposable) : ViewModel() {
    private lateinit var mNavigator: WeakReference<N>
    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
    fun setNavigator(navigator: N) {
        this.mNavigator = WeakReference(navigator)
    }
    fun getNavigator(): N? {
        return mNavigator.get()
    }
}