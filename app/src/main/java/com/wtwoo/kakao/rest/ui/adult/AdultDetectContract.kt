package com.wtwoo.kakao.rest.ui.adult

import android.net.Uri
import com.wtwoo.kakao.rest.model.AdultResultRepo
import com.wtwoo.kakao.rest.ui.base.BasePresenter
import com.wtwoo.kakao.rest.ui.base.BaseView

interface AdultDetectContract {
    interface View : BaseView<Presenter> {
        fun detectAdultSuccess(result: AdultResultRepo)
    }

    interface Presenter : BasePresenter {
        fun detectAdultResult(data: Uri)
        fun detectAdultResult(data: String)
    }
}