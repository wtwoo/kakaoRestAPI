package com.wtwoo.kakao.rest.util

import com.wtwoo.kakao.rest.model.KaKaoApiResult
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

interface RetrofitManager {
    @Multipart
    @POST("v1/vision/adult/detect")
    fun detectAdultFile(@Part file: MultipartBody.Part): Observable<KaKaoApiResult>

    @FormUrlEncoded
    @POST("v1/vision/adult/detect")
    fun detectAdultImageUrl(@Field("image_url") url: String): Observable<KaKaoApiResult>
}