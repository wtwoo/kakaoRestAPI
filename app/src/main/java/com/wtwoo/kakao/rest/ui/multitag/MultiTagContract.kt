package com.wtwoo.kakao.rest.ui.multitag

import android.net.Uri
import com.wtwoo.kakao.rest.model.MultiTagResultRepo
import com.wtwoo.kakao.rest.ui.base.BasePresenter
import com.wtwoo.kakao.rest.ui.base.BaseView

interface MultiTagContract {
    interface View : BaseView<Presenter> {
        fun multiTagSuccess(result: MultiTagResultRepo)
    }

    interface Presenter : BasePresenter {
        fun multiTagResult(data: Uri)
        fun multiTagResult(data: String)
    }
}