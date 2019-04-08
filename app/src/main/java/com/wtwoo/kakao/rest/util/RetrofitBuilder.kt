package com.wtwoo.kakao.rest.util

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val KAKAO_KEY: String = "2f3430fc72d8913565c8c19326f8668a"
    fun buildManager(): RetrofitManager {
        return Retrofit.Builder()
            .baseUrl(Utils.KAKAO_API_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientHttp())
            .build().create(RetrofitManager::class.java)
    }

    private fun clientHttp(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "KakaoAK $KAKAO_KEY")
                .build()
            chain.proceed(newRequest)
        }.build()
    }
}
