package com.wtwoo.kakao.rest.ui.choice

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.wtwoo.kakao.rest.R
import com.wtwoo.kakao.rest.model.Choice
import com.wtwoo.kakao.rest.ui.adult.AdultDetectActivity
import com.wtwoo.kakao.rest.ui.face.FaceDetectActivity
import com.wtwoo.kakao.rest.ui.multitag.MultiTagActivity
import com.wtwoo.kakao.rest.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class EntryChoiceActivity : BaseEntryChoiceActivity() {
    private val backPressedSubject = BehaviorSubject.createDefault(0L)
    private val compositeDisposable by lazy { CompositeDisposable() }
    override fun choices(): List<Choice> {
        return listOf(
            Choice(
                "멀티태그 생성",
                "멀티태그 API는 이미지 내의 콘텐츠에 대한 태그를 생성하는 API입니다. 이미지의 카테고리를 분류할 수 있도록 도움을 줍니다.",
                Intent(this, MultiTagActivity::class.java)
            ),
            Choice(
                "성인 이미지 판",
                "성인 이미지 판별 API는 이미지가 성인물에 해당하는지를 판별한 결과를 알려줍니다.",
                Intent(this, AdultDetectActivity::class.java)
            ),
            Choice(
                "얼굴 검출",
                "얼굴 검출 API는 이미지 내의 얼굴을 찾아내고, 얼굴의 위치, 특징점, 각도, 성별, 나이 등을 추정해서 반환합니다.",
                Intent(this, FaceDetectActivity::class.java)
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        start()
    }

    private fun start() {
        backPressedSubject
            .buffer(2, 1)
            .map { Pair(it[0], it[1]) }
            .map { pair -> pair.second - pair.first < BACK_KEY_DURATION }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ willFinish ->
                if (willFinish) {
                    finish()
                } else {
                    Toast.makeText(this, getString(R.string.back_pressed_message), Toast.LENGTH_SHORT).show()
                }
            }, {
                Log.d(TAG, "message : ${it.message}")
            }).let { compositeDisposable.add(it) }
    }

    override fun onBackPressed() {
        backPressedSubject.onNext(System.currentTimeMillis())
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    companion object {
        val TAG: String = EntryChoiceActivity::class.java.simpleName
        val BACK_KEY_DURATION = TimeUnit.SECONDS.toMillis(2)
    }
}