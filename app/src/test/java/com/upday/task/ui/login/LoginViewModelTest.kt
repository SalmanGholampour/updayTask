package com.upday.task.ui.login

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.upday.task.data.DataManager
import com.upday.task.data.model.auth.AuthRequest
import com.upday.task.data.model.auth.AuthResponse
import com.upday.task.data.model.image.ImageResponse
import com.upday.task.rx.TestSchedulerProvider
import com.upday.task.utils.AppConstants
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {
    @Mock
    lateinit var dataManager: DataManager

    @Mock
    lateinit var loginNavigator: LoginNavigator
    private lateinit var viewModel: LoginViewModel
    private lateinit var mTestScheduler: TestScheduler
    private lateinit var compositeDisposable: CompositeDisposable
    @Before
    @Throws(Exception::class)
    fun setUp() {
        mTestScheduler = TestScheduler()
        compositeDisposable = CompositeDisposable()
        val testSchedulerProvider = TestSchedulerProvider(mTestScheduler)
        viewModel = LoginViewModel(dataManager, testSchedulerProvider, compositeDisposable)
        viewModel.setNavigator(loginNavigator)
    }

    @Test
    fun getAccessToken() {
        val authResponse = getMockAuthResponse()
        val authRequest = getAuthRequest()
        Mockito.doReturn(Single.just(authResponse))
            .`when`(dataManager)
            .getToken(authRequest)

        viewModel.getAccessToken(authRequest)
        mTestScheduler.triggerActions()
        verify(loginNavigator, times(1)).goBack()
    }

    @Test
    fun testError() {
        val errorResponse =
            Response.error<ImageResponse>(401,
                ResponseBody.create(null, "someContent"))

        val authRequest = getAuthRequest()
        whenever(dataManager.getToken(authRequest)).thenReturn(
            Single.error(
                HttpException(
                    errorResponse
                )
            )
        )


        viewModel.getAccessToken(authRequest)
        mTestScheduler.triggerActions()
        verify(loginNavigator, times(1)).showUnAuthorizedError()
    }


    private fun getMockAuthResponse(): AuthResponse {
        return AuthResponse("SomeToken")
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