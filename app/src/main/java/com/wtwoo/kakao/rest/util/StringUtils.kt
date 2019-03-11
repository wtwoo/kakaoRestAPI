package com.wtwoo.kakao.rest.util

import com.wtwoo.kakao.rest.model.DetectAdultStatus

object StringUtils {
    fun detectAdultMessage(status: DetectAdultStatus): String {
        return if (status.adult > status.normal && status.adult > status.soft) {
            "성인 이미지일 확률이 ${status.adult * 100}% 입니다. 적용 할 수 없습니다."
        } else if (status.soft > status.normal && status.soft > status.adult) {
            "노출이 포함된 이미지일 확률이 ${status.soft * 100}% 입니다. 적용 할 수 없습니다."
        } else {
            "일반적인 이미지일 확률이 ${status.normal * 100}% 입니다."
        }
    }
}