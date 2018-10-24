package  com.innovation.rain.feature.order.create.view

import android.os.Bundle
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.app.utils.NavigateUtil
import com.innovation.rain.app.utils.showExitDialog
import com.innovation.rain.app.utils.showFragment
import com.innovation.rain.feature.account.create.view.CreateAccountFragment
import com.innovation.rain.feature.order.create.presenter.CreateOrderPresenter
import com.innovation.rain.feature.rica.home.view.RicaHomeFragment
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_create_order.*
import com.sf0404.core.application.base.fragment.BasePresenterInjectionFragment


class CreateOrderFragment : BasePresenterInjectionFragment<CreateOrderPresenter>(), CreateOrderView {

    @Inject
    lateinit var viewPresenter: CreateOrderPresenter

    override fun getPresenter() = viewPresenter

    override fun getLayoutId(): Int {
        return R.layout.fragment_create_order
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        enableButtonProceed(true)

        btn_plus.setOnClickListener {
            viewPresenter.handleCalculateButton(tv_quantity.text.toString().toInt(), true)
        }

        btn_minus.setOnClickListener {
            viewPresenter.handleCalculateButton(tv_quantity.text.toString().toInt(), false)
        }

        btnExitOrder.setOnClickListener {
            fragmentManager?.showExitDialog {
                NavigateUtil.logout(activity!!)
            }
        }

        btnProceedToRICA.setOnClickListener {
            //activity?.showFragment<RicaHomeFragment>()//todo
            activity?.showFragment<CreateAccountFragment>()//todo
        }
    }

    override fun enableButtonProceed(allowEnableProceedButton: Boolean) {
        btnProceedToRICA.isEnabled = allowEnableProceedButton
        btnProceedToRICA.isActivated = allowEnableProceedButton
    }

    override fun updateValueItemPrice(value: String) {
        tv_price.text = value
    }

    override fun updateValueItemTotalPrice(value: String) {
        tv_total_price.text = value
    }

    override fun updateValueQuantity(value: String) {
        tv_quantity.text = value
    }

    override fun updateValueSubTotalItem(value: String) {
        var item = "(" + value + " item )"
        if (value.toInt() > 1) item = "(" + value + " items )"
        tv_sub_total_item.text = item
    }
}
