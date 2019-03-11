package com.wtwoo.kakao.rest.observable

import com.wtwoo.kakao.rest.model.KaKaoApiResult
import com.wtwoo.kakao.rest.util.RetrofitBuilder
import io.reactivex.Observable
import okhttp3.MultipartBody

object DetectAdultObservable {
    fun observableDetectAdult(part: MultipartBody.Part): Observable<KaKaoApiResult> {
        val service = RetrofitBuilder.buildManager()
        return service.detectAdultFile(part)
    }

    fun observableDetectAdult(imageUrl: String): Observable<KaKaoApiResult> {
        val service = RetrofitBuilder.buildManager()
        return service.detectAdultImageUrl(imageUrl)
    }
}