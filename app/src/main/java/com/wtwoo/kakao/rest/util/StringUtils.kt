package com.wtwoo.kakao.rest.util

import com.wtwoo.kakao.rest.model.AdultResultRepo

object StringUtils {
    fun detectAdultMessage(result: AdultResultRepo.Result): String {
        return if (result.adult > result.normal && result.adult > result.soft) {
            "성인 이미지일 확률이 ${result.adult * 100}% 입니다. 적용 할 수 없습니다."
        } else if (result.soft > result.normal && result.soft > result.adult) {
            "노출이 포함된 이미지일 확률이 ${result.soft * 100}% 입니다. 적용 할 수 없습니다."
        } else {
            "일반적인 이미지일 확률이 ${result.normal * 100}% 입니다."
        }
    }
}