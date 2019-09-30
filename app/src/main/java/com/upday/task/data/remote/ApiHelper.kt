package com.upday.task.data.remote

import com.upday.task.data.model.auth.AuthRequest
import com.upday.task.data.model.auth.AuthResponse
import com.upday.task.data.model.image.ImageRequest
import com.upday.task.data.model.image.ImageResponse
import io.reactivex.Single


interface ApiHelper  {
    fun getToken(authRequest: AuthRequest):Single<AuthResponse>

    fun getImages(imageRequest: ImageRequest):Single<ImageResponse>


}