package com.innovation.rain.feature.account.create.presenter

import com.sf0404.core.application.base.presenter.BasePresenter

interface CreateAccountPresenter : BasePresenter {

    fun validateEmail(value: String, errorMess: String)
    fun validateConfirmEmail(email: String, confirmEmail: String, errorMess: String)
    fun validatePassword(value: String, errorMess: String)
    fun validateConfirmPassword(pass: String, confirmPass: String, errorMess: String)
    fun goToNextScreen()
}