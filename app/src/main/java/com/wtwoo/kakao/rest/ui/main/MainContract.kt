package com.wtwoo.kakao.rest.ui.main

import android.net.Uri
import com.wtwoo.kakao.rest.model.KaKaoApiResult
import com.wtwoo.kakao.rest.ui.base.BasePresenter
import com.wtwoo.kakao.rest.ui.base.BaseView

interface MainContract {
    interface View : BaseView<Presenter> {
        fun detectAdultSuccess(result: KaKaoApiResult)
    }

    interface Presenter : BasePresenter {
        fun detectAdultResult(data: Uri)
        fun detectAdultResult(data: String)
    }
}