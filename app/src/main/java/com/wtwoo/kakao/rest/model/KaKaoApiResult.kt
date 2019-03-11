package com.wtwoo.kakao.rest.model

import com.google.gson.annotations.SerializedName

data class KaKaoApiResult(
    @SerializedName("result")
    var detectAdultStatus: DetectAdultStatus
)