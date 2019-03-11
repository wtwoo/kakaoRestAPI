package com.wtwoo.kakao.rest.ui.main

import android.Manifest
import android.app.Activity
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.wtwoo.kakao.rest.R
import com.wtwoo.kakao.rest.observable.DetectAdultObservable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private val compositeDisposable by lazy { CompositeDisposable() }

    private fun getActivity(): Activity {
        return view as Activity
    }

    override fun clearDisposable() {
        compositeDisposable.clear()
    }

    override fun start() {
        initTedPermission()
    }

    private fun initTedPermission() {
        TedPermission.with(getActivity())
            .setPermissionListener(permissionListener)
            .setDeniedMessage(getActivity().getString(R.string.grant_permissions_settings))
            .setPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .check()
    }

    private val permissionListener = object : PermissionListener {
        override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
            getActivity().finish()
        }

        override fun onPermissionGranted() {
            Log.d(TAG, "Permission Granted")
        }
    }

    override fun detectAdultResult(imageUrl: String) {
        view.showProgressDialog()
        DetectAdultObservable.observableDetectAdult(imageUrl)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.hideProgressDialog()
                view.detectAdultSuccess(it)
                Log.d(TAG, "detectAdultResult : ${it.detectAdultStatus}")
            }, {
                view.hideProgressDialog()
                Log.d(TAG, "detectAdultResult error: $it")
                Toast.makeText(getActivity(), it.message, Toast.LENGTH_SHORT).show()
            }).let { compositeDisposable.add(it) }
    }

    override fun detectAdultResult(data: Uri) {
        val file = File(data.path)

        val requestFile: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val filePart = MultipartBody.Part.createFormData("file", file.name, requestFile)

        view.showProgressDialog()
        DetectAdultObservable.observableDetectAdult(filePart)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.hideProgressDialog()
                view.detectAdultSuccess(it)
                Log.d(TAG, "detectAdultResult : ${it.detectAdultStatus}")
            }, {
                view.hideProgressDialog()
                Log.d(TAG, "detectAdultResult error: $it")
                Toast.makeText(getActivity(), it.message, Toast.LENGTH_SHORT).show()
            }).let { compositeDisposable.add(it) }
    }

    companion object {
        val TAG: String = MainPresenter::class.java.simpleName
    }
}