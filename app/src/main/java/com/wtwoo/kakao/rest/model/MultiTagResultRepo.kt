package com.wtwoo.kakao.rest.model

import com.google.gson.annotations.SerializedName

data class MultiTagResultRepo(
    @SerializedName("result")
    val result: Result?
) {
    data class Result(
        @SerializedName("label")
        var label: List<String>? = null,
        @SerializedName("label_kr")
        var labelKr: List<String>? = null
    )
}