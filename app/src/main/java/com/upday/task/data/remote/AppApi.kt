package com.upday.task.data.remote

import com.upday.task.data.model.auth.AuthResponse
import com.upday.task.data.model.image.ImageResponse
import io.reactivex.Single
import retrofit2.http.*

interface AppApi {


    @FormUrlEncoded
    @POST("oauth/access_token")
    fun getToken(
        @Field("client_id") clientID: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String,
        @Field("grant_type") grantType: String,
        @Field("realm") realm: String
    ): Single<AuthResponse>

    @GET("images/search")
    fun getImages(
        @Header("Authorization") access_token: String, @Query("per_page") perPage: Int, @Query(
            "page"
        ) page: Int
    ): Single<ImageResponse>
}