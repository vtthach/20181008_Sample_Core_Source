package  com.innovation.rain.feature.account.create.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.innovation.rain.feature.account.create.presenter.CreateAccountPresenter
import com.sf0404.core.application.base.fragment.BasePresenterInjectionFragment
import com.innovation.rain.R
import kotlinx.android.synthetic.main.fragment_create_account.*
import javax.inject.Inject


class CreateAccountFragment : BasePresenterInjectionFragment<CreateAccountPresenter>(), CreateAccountView {

    @Inject
    lateinit var viewPresenter: CreateAccountPresenter

    override fun getPresenter() = viewPresenter

    override fun getLayoutId(): Int {
        return R.layout.fragment_create_account
    }

    private val passwordChangeListener: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable) {
            viewPresenter.validateEmail(s.toString())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPresenter.setUserId()

        edtEmail.addTextChangedListener(passwordChangeListener)

    }

    override fun setUserId(userId: String) {
        edtUserId.text = Editable.Factory.getInstance().newEditable(userId)
    }

    override fun showEmailError(value: String) {
        tvEmailError.text = getString(R.string.email_error)
    }

    override fun showConfirmEmailError(value: String) {
        tvConfirmEmailError.text = getString(R.string.confirm_email_error)
    }

    override fun showPasswordError(value: String) {
        tvPasswordError.text = getString(R.string.password_error)
    }

    override fun showConfirmPasswordError(value: String) {
        tvConfirmPasswordError.text = getString(R.string.confirm_password_error)
    }
}
