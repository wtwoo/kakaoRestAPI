package com.wtwoo.kakao.rest.ui.base

interface BaseView<T> {
    var presenter: T
    fun showProgressDialog()
    fun hideProgressDialog()
}