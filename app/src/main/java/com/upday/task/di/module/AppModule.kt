package com.upday.task.di.module

import android.app.Application
import android.content.Context
import com.upday.task.data.AppDataManager
import com.upday.task.data.DataManager
import com.upday.task.data.remote.ApiHelper
import com.upday.task.data.remote.AppApiHelper
import com.upday.task.BuildConfig
import com.upday.task.R
import com.upday.task.data.local.AppPreferencesHelper
import com.upday.task.data.local.PreferencesHelper
import com.upday.task.data.remote.AppApi
import com.upday.task.di.PreferenceInfo
import com.upday.task.utils.AppConstants
import com.upday.task.utils.rx.AppSchedulerProvider
import com.upday.task.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.CipherSuite
import okhttp3.TlsVersion
import okhttp3.ConnectionSpec
import java.util.*


@Module
class AppModule {
    @Provides
    @Singleton
    fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper {
        return appApiHelper
    }

    @Provides
    @Singleton
    fun providePreferenceHelper(appPreferencesHelper: AppPreferencesHelper): PreferencesHelper {
        return appPreferencesHelper
    }
    @Provides
    @PreferenceInfo
    internal fun providePreferenceName(): String {
        return AppConstants.PREF_NAME
    }

    @Provides
    @Singleton
    internal fun provideImageApi(retrofit: Retrofit): AppApi {
        return retrofit.create(AppApi::class.java)
    }


    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val spec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            .tlsVersions(TlsVersion.TLS_1_2)
            .cipherSuites(
                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256
            )
            .build()
        return OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .connectionSpecs(Collections.singletonList(spec))
            .build()
    }

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    internal fun provideCompositeDisposableProvider(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @Singleton
    internal fun provideCalligraphyDefaultConfig(): CalligraphyConfig {
        return CalligraphyConfig.Builder()
            .setDefaultFontPath("fonts/roboto.ttf")
            .setFontAttrId(R.attr.fontPath)
            .build()
    }


}
