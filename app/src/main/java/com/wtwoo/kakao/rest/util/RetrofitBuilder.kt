package com.wtwoo.kakao.rest.util

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
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
                .addHeader("Authorization", "KakaoAK " + "ba11a2dee15fb15761204a710ea72798")
                .build()
            chain.proceed(newRequest)
        }.build()
    }
}
