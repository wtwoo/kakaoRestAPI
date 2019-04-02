package com.wtwoo.kakao.rest.ui.choice

import android.content.Intent
import com.wtwoo.kakao.rest.model.Choice
import com.wtwoo.kakao.rest.ui.adult.AdultDetectActivity
import com.wtwoo.kakao.rest.ui.face.FaceDetectActivity

class EntryChoiceActivity : BaseEntryChoiceActivity() {
    override fun choices(): List<Choice> {
        return listOf(
            Choice(
                "성인 이미지 판",
                "성인 이미지 판별 API는 이미지가 성인물에 해당하는지를 판별한 결과를 알려줍니다",
                Intent(this, AdultDetectActivity::class.java)
            ),
            Choice(
                "얼굴 검출",
                "얼굴 검출 API는 이미지 내의 얼굴을 찾아내고, 얼굴의 위치, 특징점, 각도, 성별, 나이 등을 추정해서 반환합니다..",
                Intent(this, FaceDetectActivity::class.java))
        )
    }
}