package com.wtwoo.kakao.rest.ui.face

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import com.wtwoo.kakao.rest.R
import com.wtwoo.kakao.rest.ext.loadUrl
import com.wtwoo.kakao.rest.model.FaceResultRepo
import com.wtwoo.kakao.rest.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_face_detect.*


class FaceDetectActivity : BaseActivity(), FaceDetectContract.View {
    override lateinit var presenter: FaceDetectContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_face_detect)
        init()
        initListener()
    }

    private fun init() {
        presenter = FaceDetectPresenter(this)
        presenter.start()
    }

    private fun initListener() {
        ImageUrlButton.setOnClickListener {
            imageView.loadUrl(imageUrl)
            presenter.detectFaceResult(imageUrl)
        }

        galleryButton.setOnClickListener {
            onSelectImageClick()
        }
    }

    private fun onSelectImageClick() {
        CropImage.activity()
            .setGuidelines(CropImageView.Guidelines.ON)
            .setActivityTitle(getString(R.string.profile_activity_label))
            .setCropShape(CropImageView.CropShape.RECTANGLE)
            .setCropMenuCropButtonTitle(getString(R.string.select))
            .start(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, result: Intent?) {
        Log.d(TAG, "onActivityResult:$requestCode:$resultCode:$result")
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(result)
            if (resultCode == RESULT_OK) {
                presenter.detectFaceResult(result.uri)
                imageView.loadUrl(result.uri)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " + result.error, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun detectFaceSuccess(result: FaceResultRepo) {
        Log.d(TAG, "detectAdultSuccess result : $result")
        var age = ""

        result.result?.faces?.run {
            forEachIndexed { index, face ->
                age += if (index == (size - 1)) {
                    "${Math.floor(face.facialAttributes?.age ?: 0.0).toInt()}살"
                } else {
                    "${Math.floor(face.facialAttributes?.age ?: 0.0).toInt()}살,"
                }
            }
        }

        resultTextView.text = age
        showToast(age)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.clearDisposable()
    }

    companion object {
        const val imageUrl = "https://t1.daumcdn.net/alvolo/_vision/openapi/r2/images/01.jpg"
        //const val imageUrl = "https://t1.daumcdn.net/alvolo/_vision/openapi/r2/images/05.jpg"
        val TAG: String = FaceDetectActivity::class.java.simpleName
    }
}