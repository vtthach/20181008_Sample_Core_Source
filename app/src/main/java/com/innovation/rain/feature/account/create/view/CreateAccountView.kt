package  com.innovation.rain.feature.account.create.view

import com.sf0404.core.application.base.presenter.BaseView
import kiosk.consumer.cbsa.passwordstrengthchecker.PasswordStrengthView

interface CreateAccountView : BaseView {

    fun initUserId(userId: String)
    fun showEmailError(value: String)
    fun showConfirmEmailError(value: String)
    fun showPasswordError(value: String)
    fun showConfirmPasswordError(value: String)
    fun enableButtonProceed(allowEnableProceedButton: Boolean)
    fun goToNextScreen()
    fun applyConditions(conditions: List<PasswordStrengthView.Condition>)
}