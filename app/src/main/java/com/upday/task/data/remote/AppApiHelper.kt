package com.upday.task.data.remote


import com.upday.task.data.model.auth.AuthRequest
import com.upday.task.data.model.auth.AuthResponse
import com.upday.task.data.model.image.ImageRequest
import com.upday.task.data.model.image.ImageResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppApiHelper @Inject constructor() : ApiHelper {
    @Inject
    internal lateinit var appApi: AppApi

    override fun getToken(authRequest: AuthRequest)

            : Single<AuthResponse> = appApi.getToken(
        authRequest.clientId,
        authRequest.clientSecret,
        authRequest.code,
        authRequest.grantType,
        authRequest.tokenRealm
    )

    override fun getImages(imageRequest: ImageRequest): Single<ImageResponse> {
        return appApi.getImages(imageRequest.accessToken, imageRequest.perPage, imageRequest.page)
    }

}