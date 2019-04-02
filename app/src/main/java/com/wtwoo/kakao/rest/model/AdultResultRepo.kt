package com.wtwoo.kakao.rest.model

import com.google.gson.annotations.SerializedName

data class AdultResultRepo(
    @SerializedName("result")
    val result: Result?
) {
    data class Result(
        var normal: Float = 0.0f,
        var soft: Float = 0.0f,
        var adult: Float = 0.0f
    )
}