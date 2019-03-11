package com.wtwoo.kakao.rest.model

data class DetectAdultStatus(
        var normal: Float = 0.0f,
        var soft: Float = 0.0f,
        var adult: Float = 0.0f
)