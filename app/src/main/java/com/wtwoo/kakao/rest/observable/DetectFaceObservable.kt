package com.wtwoo.kakao.rest.observable

import com.wtwoo.kakao.rest.model.FaceResultRepo
import com.wtwoo.kakao.rest.util.RetrofitBuilder
import io.reactivex.Observable
import okhttp3.MultipartBody

object DetectFaceObservable {
    fun observableDetectFace(part: MultipartBody.Part): Observable<FaceResultRepo> {
        val service = RetrofitBuilder.buildManager()
        return service.detectFaceFile(part)
    }

    fun observableDetectFace(imageUrl: String): Observable<FaceResultRepo> {
        val service = RetrofitBuilder.buildManager()
        return service.detectFaceImageUrl(imageUrl)
    }
}