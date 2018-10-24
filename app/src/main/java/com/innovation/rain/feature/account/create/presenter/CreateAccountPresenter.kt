package com.innovation.rain.feature.account.create.presenter

import com.sf0404.core.application.base.presenter.BasePresenter

interface CreateAccountPresenter : BasePresenter {

    fun setUserId()
    fun validateEmail(value: String)
    fun validateConfirmEmail(value: String)
    fun validatePassword(value: String)
    fun validateConfirmPassword(value: String)
}