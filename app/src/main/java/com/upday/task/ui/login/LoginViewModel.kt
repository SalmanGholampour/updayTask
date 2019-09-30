package com.upday.task.ui.login

import com.upday.task.base.BaseViewModel
import com.upday.task.data.DataManager
import com.upday.task.data.model.auth.AuthRequest
import com.upday.task.ui.error.GlobalErrorHandler
import com.upday.task.utils.AppConstants
import com.upday.task.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class LoginViewModel(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BaseViewModel<LoginNavigator>(dataManager, schedulerProvider, compositeDisposable) {
fun onLoginClick(){
    val authRequest=getAuthRequest()
    getAccessToken(authRequest)
}
    fun getAccessToken(authRequest: AuthRequest) {
        compositeDisposable.add(
            dataManager.getToken(authRequest).subscribeOn(schedulerProvider.io()).observeOn(
                schedulerProvider.ui()
            ).subscribe({
                dataManager.setAccessToken("Bearer " + it.token)
                getNavigator()?.goBack()
            }) {
                getNavigator()?.let { it1 -> GlobalErrorHandler.handle(it, it1) }
            })
    }

    private fun getAuthRequest(): AuthRequest {
        return AuthRequest(
            AppConstants.ACCESS_TOKEN_CLIENT_ID,
            AppConstants.ACCESS_TOKEN_CLIENT_SECRET,
            AppConstants.ACCESS_TOKEN_CODE,
            AppConstants.ACCESS_TOKEN_GRANT_TYPE,
            AppConstants.ACCESS_TOKEN_REALM
        )
    }
}