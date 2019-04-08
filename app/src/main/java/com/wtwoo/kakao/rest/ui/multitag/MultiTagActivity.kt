package com.wtwoo.kakao.rest.ui.multitag

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import com.wtwoo.kakao.rest.R
import com.wtwoo.kakao.rest.ext.loadUrl
import com.wtwoo.kakao.rest.model.MultiTagResultRepo
import com.wtwoo.kakao.rest.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_face_detect.*

class MultiTagActivity : BaseActivity(), MultiTagContract.View {
    override lateinit var presenter: MultiTagContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default)
        init()
        initListener()
    }

    private fun init() {
        presenter = MultiTagPresenter(this)
        presenter.start()
    }

    private fun initListener() {
        ImageUrlButton.setOnClickListener {
            imageView.loadUrl(imageUrl)
            presenter.multiTagResult(imageUrl)
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
                presenter.multiTagResult(result.uri)
                imageView.loadUrl(result.uri)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " + result.error, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun multiTagSuccess(result: MultiTagResultRepo) {
        Log.d(TAG, "detectAdultSuccess result : $result")
        val resultString = result.result?.labelKr.toString()
        resultTextView.text = resultString
        showToast(resultString)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.clearDisposable()
    }

    companion object {
        const val imageUrl = "https://t1.daumcdn.net/alvolo/_vision/openapi/r2/images/08.jpg"
        val TAG: String = MultiTagActivity::class.java.simpleName
    }
}