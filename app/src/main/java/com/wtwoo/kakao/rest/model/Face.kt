package com.wtwoo.kakao.rest.model

import com.google.gson.annotations.SerializedName

data class Face(

    @SerializedName("facial_attributes")
    var facialAttributes: FacialAttributes? = null,

    @SerializedName("facial_points")
    var facialPoints: FacialPoints? = null,

    @SerializedName("class_idx")
    var classIdx: Int? = null,

    var score: Double? = null,
    var x: Double? = null,
    var y: Double? = null,
    var w: Double? = null,
    var h: Double? = null,
    var pitch: Double? = null,
    var yaw: Double? = null,
    var roll: Double? = null
)