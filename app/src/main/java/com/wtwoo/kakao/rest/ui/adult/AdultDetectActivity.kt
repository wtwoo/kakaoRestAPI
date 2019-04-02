package com.wtwoo.kakao.rest.ui.adult

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import com.wtwoo.kakao.rest.R
import com.wtwoo.kakao.rest.ext.loadUrl
import com.wtwoo.kakao.rest.model.AdultResultRepo
import com.wtwoo.kakao.rest.ui.base.BaseActivity
import com.wtwoo.kakao.rest.util.Log
import com.wtwoo.kakao.rest.util.StringUtils
import kotlinx.android.synthetic.main.activity_adult_detect.*

class AdultDetectActivity : BaseActivity(), AdultDetectContract.View {
    override lateinit var presenter: AdultDetectContract.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adult_detect)
        init()
        initListener()
    }

    private fun init() {
        presenter = AdultDetectPresenter(this)
        presenter.start()
    }

    private fun initListener() {
        ImageUrlButton.setOnClickListener {
            imageView.loadUrl(imageUrl)
            presenter.detectAdultResult(imageUrl)
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
                presenter.detectAdultResult(result.uri)
                imageView.loadUrl(result.uri)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " + result.error, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun detectAdultSuccess(adultResultRepo: AdultResultRepo) {
        Log.d(TAG, "detectAdultSuccess result : $adultResultRepo")
        var message = ""
        adultResultRepo.result?.let {
             message = StringUtils.detectAdultMessage(it)
        }

        resultTextView.text = message
        showToast(message)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.clearDisposable()
    }

    companion object {
        const val imageUrl = "https://t1.daumcdn.net/alvolo/_vision/openapi/r2/images/09.jpg"
        val TAG: String = AdultDetectActivity::class.java.simpleName
    }
}