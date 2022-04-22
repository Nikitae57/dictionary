package ru.nikitae57.data.di

import android.util.Log
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.nikitae57.data.translation.gettranslation.YandexTokenApi
import ru.nikitae57.domain.core.SchedulerProvider
import ru.nikitae57.domain.core.token.TokenProvider
import java.util.concurrent.TimeUnit

@Module
class DataModule {
    @Provides
    fun providesTokenApi(retrofit: Retrofit): YandexTokenApi {
        return retrofit.create(YandexTokenApi::class.java)
    }

    @Provides
    fun providesRetrofit(
        schedulerProvider: SchedulerProvider,
        tokenProvider: TokenProvider
    ): Retrofit {
        val rxJava = RxJava2CallAdapterFactory.createWithScheduler(schedulerProvider.io())
        val httpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val newRequest = originalRequest.newBuilder()
                    .addHeader("Authorization", "Bearer ${tokenProvider.getToken()}")
                    .build()

                Log.d("adfsdf", tokenProvider.getToken())
                chain.proceed(newRequest)
            }
            .callTimeout(10L, TimeUnit.SECONDS)
            .connectTimeout(10L, TimeUnit.SECONDS)
            .readTimeout(10L, TimeUnit.SECONDS)
            .writeTimeout(10L, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://translate.api.cloud.yandex.net")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(rxJava)
            .client(httpClient)
            .build()
    }
}