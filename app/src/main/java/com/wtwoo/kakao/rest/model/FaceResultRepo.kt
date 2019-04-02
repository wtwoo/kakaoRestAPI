package com.wtwoo.kakao.rest.model

import com.google.gson.annotations.SerializedName

data class FaceResultRepo(
    @SerializedName("result")
    val result: Result?
) {
    data class Result(
        val width: Int,
        val height: Int,
        var faces: List<Face>? = null
    )
}