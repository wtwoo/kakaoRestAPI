package com.wtwoo.kakao.rest.observable

import com.wtwoo.kakao.rest.model.MultiTagResultRepo
import com.wtwoo.kakao.rest.util.RetrofitBuilder
import io.reactivex.Observable
import okhttp3.MultipartBody

object MultiTagObservable {
    fun observableMultiTag(part: MultipartBody.Part): Observable<MultiTagResultRepo> {
        val service = RetrofitBuilder.buildManager()
        return service.multiTagFile(part)
    }

    fun observableMultiTag(imageUrl: String): Observable<MultiTagResultRepo> {
        val service = RetrofitBuilder.buildManager()
        return service.multiTagImageUrl(imageUrl)
    }
}