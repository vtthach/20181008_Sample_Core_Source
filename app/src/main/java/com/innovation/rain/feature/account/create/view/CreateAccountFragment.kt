package  com.innovation.rain.feature.account.create.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.innovation.rain.feature.account.create.presenter.CreateAccountPresenter
import com.sf0404.core.application.base.fragment.BasePresenterInjectionFragment
import com.innovation.rain.R
import com.innovation.rain.app.utils.NavigateUtil
import com.innovation.rain.app.utils.showExitDialog
import com.innovation.rain.app.utils.showFragment
import com.innovation.rain.feature.rica.home.view.RicaHomeFragment
import kiosk.consumer.cbsa.passwordstrengthchecker.PasswordStrengthView
import kotlinx.android.synthetic.main.fragment_create_account.*
import javax.inject.Inject


class CreateAccountFragment : BasePresenterInjectionFragment<CreateAccountPresenter>(), CreateAccountView {

    @Inject
    lateinit var viewPresenter: CreateAccountPresenter

    override fun getPresenter() = viewPresenter

    override fun getLayoutId(): Int {
        return R.layout.fragment_create_account
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        enableButtonProceed(true)

        vPasswordStrength.setListener(edtPassword) {

        }

        btnExitAccount.setOnClickListener {
            fragmentManager?.showExitDialog {
                NavigateUtil.logout(activity!!)
            }
        }

        btnProceedToPayment.setOnClickListener {
            viewPresenter.validateEmail(edtEmail.text.toString(), getString(R.string.email_error))
            viewPresenter.validateConfirmEmail(edtEmail.text.toString(), edtConfirmEmail.text.toString(), getString(R.string.confirm_email_error))
            viewPresenter.validatePassword(edtPassword.text.toString(), getString(R.string.password_error))
            viewPresenter.validateConfirmPassword(edtPassword.text.toString(), edtConfirmPassword.text.toString(), getString(R.string.confirm_password_error))
            viewPresenter.goToNextScreen()
        }
    }

    override fun enableButtonProceed(allowEnableProceedButton: Boolean) {
        btnProceedToPayment.isEnabled = allowEnableProceedButton
        btnProceedToPayment.isActivated = allowEnableProceedButton
    }

    override fun initUserId(userId: String) {
        edtUserId.text = Editable.Factory.getInstance().newEditable(userId)
    }

    override fun showEmailError(value: String) {
        tvEmailError.text = value
    }

    override fun showConfirmEmailError(value: String) {
        tvConfirmEmailError.text = value
    }

    override fun showPasswordError(value: String) {
        tvPasswordError.text = value
    }

    override fun showConfirmPasswordError(value: String) {
        tvConfirmPasswordError.text = value
    }

    override fun goToNextScreen() {
        activity?.showFragment<RicaHomeFragment>()
    }

    override fun applyConditions(conditions: List<PasswordStrengthView.Condition>) {
        vPasswordStrength.conditions = conditions
    }
}
