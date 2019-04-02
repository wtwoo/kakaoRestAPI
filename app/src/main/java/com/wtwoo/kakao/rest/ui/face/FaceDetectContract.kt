package com.wtwoo.kakao.rest.ui.face

import android.net.Uri
import com.wtwoo.kakao.rest.model.FaceResultRepo
import com.wtwoo.kakao.rest.ui.base.BasePresenter
import com.wtwoo.kakao.rest.ui.base.BaseView

interface FaceDetectContract {
    interface View : BaseView<Presenter> {
        fun detectFaceSuccess(result: FaceResultRepo)
    }

    interface Presenter : BasePresenter {
        fun detectFaceResult(data: Uri)
        fun detectFaceResult(data: String)
    }
}