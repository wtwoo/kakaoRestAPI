package com.wtwoo.kakao.rest.observable

import com.wtwoo.kakao.rest.model.AdultResultRepo
import com.wtwoo.kakao.rest.util.RetrofitBuilder
import io.reactivex.Observable
import okhttp3.MultipartBody

object DetectAdultObservable {
    fun observableDetectAdult(part: MultipartBody.Part): Observable<AdultResultRepo> {
        val service = RetrofitBuilder.buildManager()
        return service.detectAdultFile(part)
    }

    fun observableDetectAdult(imageUrl: String): Observable<AdultResultRepo> {
        val service = RetrofitBuilder.buildManager()
        return service.detectAdultImageUrl(imageUrl)
    }
}