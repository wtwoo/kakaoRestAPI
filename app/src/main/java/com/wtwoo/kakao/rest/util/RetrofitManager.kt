package com.wtwoo.kakao.rest.util

import com.wtwoo.kakao.rest.model.FaceResultRepo
import com.wtwoo.kakao.rest.model.AdultResultRepo
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

interface RetrofitManager {
    @Multipart
    @POST("v1/vision/adult/detect")
    fun detectAdultFile(@Part file: MultipartBody.Part): Observable<AdultResultRepo>

    @FormUrlEncoded
    @POST("v1/vision/adult/detect")
    fun detectAdultImageUrl(@Field("image_url") url: String): Observable<AdultResultRepo>


    @FormUrlEncoded
    @POST("v1/vision/face/detect")
    fun detectFaceImageUrl(@Field("image_url") url: String): Observable<FaceResultRepo>

    @Multipart
    @POST("v1/vision/face/detect")
    fun detectFaceFile(@Part file: MultipartBody.Part): Observable<FaceResultRepo>

}