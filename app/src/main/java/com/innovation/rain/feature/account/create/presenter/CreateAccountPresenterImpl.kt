package com.innovation.rain.feature.account.create.presenter

import android.os.Bundle
import com.innovation.rain.feature.account.create.view.CreateAccountView
import com.sf0404.core.application.base.presenter.BasePresenterImpl

import javax.inject.Inject


class CreateAccountPresenterImpl @Inject
constructor(view: CreateAccountView) : BasePresenterImpl<CreateAccountView>(view), CreateAccountPresenter {

    override fun onViewCreated(savedInstanceState: Bundle?, arguments: Bundle?) {
        super.onViewCreated(savedInstanceState, arguments)
        initUserId()
    }

    override fun validateEmail(value: String) {
        if(validateStringEmpty(value)) view.showEmailError(value)
        else view.showEmailError("")
    }

    override fun validateConfirmEmail(email: String, confirmEmail: String) {
        if(email != confirmEmail ) view.showEmailError("Error")
        else view.showEmailError("")
    }

    override fun validatePassword(value: String) {
        if(validateStringEmpty(value)) view.showEmailError(value)
        else view.showEmailError("")
    }

    override fun validateConfirmPassword(pass: String, confirmPass: String) {
        if(pass != confirmPass ) view.showEmailError("Error")
        else view.showEmailError("")
    }

    private fun initUserId() {
        val userId = "9110030037087"
        view.initUserId(userId)
    }

    private fun validateStringEmpty(value: String) : Boolean{
        return value.length > 0
    }


}
