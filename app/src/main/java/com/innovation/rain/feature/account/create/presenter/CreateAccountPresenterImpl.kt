package com.innovation.rain.feature.account.create.presenter

import android.os.Bundle
import com.innovation.rain.feature.account.create.view.CreateAccountView
import com.sf0404.core.application.base.presenter.BasePresenterImpl
import kiosk.consumer.cbsa.passwordstrengthchecker.conditions.*
import javax.inject.Inject
import android.text.TextUtils


class CreateAccountPresenterImpl @Inject
constructor(view: CreateAccountView) : BasePresenterImpl<CreateAccountView>(view), CreateAccountPresenter {

    override fun onViewCreated(savedInstanceState: Bundle?, arguments: Bundle?) {
        super.onViewCreated(savedInstanceState, arguments)
        initUserId()
        view.applyConditions(conditions)
    }

    var isEmailError = false
    var isConfirmEmailError = false
    var isPasswordError = false
    var isConfirmPasswordError = false
    //Password Strength
    val oneLowercaseCondition = OneLowercaseCondition()
    val oneUppercaseCondition = OneUppercaseCondition()
    val oneNumberCondition = OneNumberCondition()
    val atLeast8CharsCondition = AtLeast8CharsCondition()
    val notCommonTextCondition = NotCommonTextCondition(arrayListOf("1111","2222","3333","4444","5555","6666","7777","8888","9999"))
    val oneSpecialCharsCondition = OneSpecialCharsCondition()
    val conditions = mutableListOf(
            oneLowercaseCondition,
            oneUppercaseCondition,
            oneNumberCondition,
            atLeast8CharsCondition,
            notCommonTextCondition,
            oneSpecialCharsCondition
    )

    override fun validateEmail(value: String, errorMess: String) {
        if(!isValidEmail(value)){
            view.showEmailError(errorMess)
            isEmailError = true
        }else {
            view.showEmailError("")
            isEmailError = false
        }
    }

    override fun validateConfirmEmail(email: String, confirmEmail: String, errorMess: String) {
        if(email != confirmEmail ) {
            view.showConfirmEmailError(errorMess)
            isConfirmEmailError = true
        }else {
            view.showConfirmEmailError("")
            isConfirmEmailError = false
        }
    }

    override fun validatePassword(value: String, errorMess: String) {
        if(!isLength8Chars(value) || !haveNumberDigits(value)) {
            view.showPasswordError(errorMess)
            isPasswordError = true
        }else {
            view.showPasswordError("")
            isPasswordError = false
        }
    }

    override fun validateConfirmPassword(pass: String, confirmPass: String, errorMess: String) {
        if(pass != confirmPass ) {
            view.showConfirmPasswordError(errorMess)
            isConfirmPasswordError = true
        }else {
            view.showConfirmPasswordError("")
            isConfirmPasswordError = false
        }
    }

    override fun goToNextScreen() {
        if(!isEmailError && !isConfirmEmailError && !isPasswordError && !isConfirmPasswordError) view.goToNextScreen()
    }

    private fun initUserId() {
        val userId = "9110030037087"
        view.initUserId(userId)
    }

    private fun isLength8Chars(value: String) : Boolean{
        return value.length >= 8
    }

    private fun haveNumberDigits(s: String): Boolean {
        var numDigits = 0
        val length = s.length
        for (i in 0 until length) {
            if (Character.isDigit(s[i])) {
                numDigits++
            }
        }
        return numDigits > 0
    }

    private fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}
