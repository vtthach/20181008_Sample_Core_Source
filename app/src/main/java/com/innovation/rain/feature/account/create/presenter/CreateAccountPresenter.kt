package com.innovation.rain.feature.account.create.presenter

import com.sf0404.core.application.base.presenter.BasePresenter

interface CreateAccountPresenter : BasePresenter {

    fun validateEmail(value: String)
    fun validateConfirmEmail(email: String, confirmEmail: String)
    fun validatePassword(value: String)
    fun validateConfirmPassword(pass: String, confirmPass: String)
}