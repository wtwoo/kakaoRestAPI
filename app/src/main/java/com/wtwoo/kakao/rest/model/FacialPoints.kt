package com.wtwoo.kakao.rest.model

import com.google.gson.annotations.SerializedName

data class FacialPoints(

    var jaw: List<List<Double>>? = null,
    var nose: List<List<Double>>? = null,
    var lip: List<List<Double>>? = null,

    @SerializedName("right_eyebrow")
    var right_eyebrow: List<List<Double>>? = null,

    @SerializedName("left_eyebrow")
    var left_eyebrow: List<List<Double>>? = null,

    @SerializedName("right_eye")
    var right_eye: List<List<Double>>? = null,

    @SerializedName("left_eye")
    var left_eye: List<List<Double>>? = null
)
