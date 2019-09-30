package com.upday.task.data


import com.upday.task.data.remote.ApiHelper
import com.upday.task.data.local.PreferencesHelper
import com.upday.task.data.model.auth.AuthRequest
import com.upday.task.data.model.auth.AuthResponse
import com.upday.task.data.model.image.ImageRequest
import com.upday.task.data.model.image.ImageResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(
    private val mApiHelper: ApiHelper,
    private val mPreferencesHelper: PreferencesHelper
) : DataManager {


    override fun setAccessToken(accessToken: String) {
        return mPreferencesHelper.setAccessToken(accessToken)
    }

    override fun getAccessToken(): String {
        return mPreferencesHelper.getAccessToken()
    }

    override fun getToken(authRequest: AuthRequest): Single<AuthResponse> {
        return mApiHelper.getToken(authRequest)
    }

    override fun getImages(imageRequest: ImageRequest): Single<ImageResponse> {
        imageRequest.accessToken = mPreferencesHelper.getAccessToken()
        return mApiHelper.getImages(imageRequest)
    }


}