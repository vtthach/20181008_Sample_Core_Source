package com.innovation.rain.feature.account.create.presenter

import com.innovation.rain.feature.account.create.view.CreateAccountView
import com.sf0404.core.application.base.presenter.BasePresenterImpl

import javax.inject.Inject


class CreateAccountPresenterImpl @Inject
constructor(view: CreateAccountView) : BasePresenterImpl<CreateAccountView>(view), CreateAccountPresenter {

    override fun validateEmail(value: String) {
        if(!validateStringEmpty(value)) view.showEmailError(value)
        else view.showEmailError("")
    }

    override fun validateConfirmEmail(value: String) {

    }

    override fun validatePassword(value: String) {

    }

    override fun validateConfirmPassword(value: String) {

    }

    override fun setUserId() {
        val userId = "9110030037087"
        view.setUserId(userId)
    }

    private fun validateStringEmpty(value: String) : Boolean{
        return value.length > 0
    }


}
