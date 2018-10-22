package  com.innovation.rain.feature.selectquantity.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.innovation.rain.R
import com.innovation.rain.R.id.*
import com.innovation.rain.app.utils.showFragment
import com.innovation.rain.feature.selectquantity.presenter.SelectQuantityPresenter
import com.innovation.rain.feature.welcomemenu.view.WelcomeMenuFragment
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_select_quantity.*
import com.sf0404.core.application.base.fragment.BasePresenterInjectionFragment
import kotlinx.android.synthetic.main.agent_login_fragment.*
import kotlinx.android.synthetic.main.fragment_rica_home.*
import kotlinx.android.synthetic.main.fragment_select_quantity.view.*


class SelectQuantityFragment : BasePresenterInjectionFragment<SelectQuantityPresenter>(), SelectQuantityView {

    @Inject
    lateinit var viewPresenter: SelectQuantityPresenter

    override fun getPresenter() = viewPresenter

    override fun getLayoutId(): Int {
        return R.layout.fragment_select_quantity
    }
    private val spinnerItemSelectedListener: AdapterView.OnItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            viewPresenter.handleSpinnerChange(itemSpinner.selectedItem.toString())
        }

        override fun onNothingSelected(parent: AdapterView<*>) {

        }
    }

    private fun initItemsSpinner(){
        val listItemsTxt = arrayOf("R50 once off", "R100 once off", "R200 once off", "R300 once off")
        itemSpinner.adapter = ArrayAdapter(activity, R.layout.view_drop_down_menu, listItemsTxt)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initItemsSpinner()

        enableButtonProceed(true)

        itemSpinner.onItemSelectedListener = spinnerItemSelectedListener

        btn_plus.setOnClickListener {
            viewPresenter.handleCalculateButton(tv_quantity.text.toString().toInt(), tv_price.text.toString().toDouble(), true)
        }

        btn_minus.setOnClickListener {
            viewPresenter.handleCalculateButton(tv_quantity.text.toString().toInt(), tv_price.text.toString().toDouble(), false)
        }

        btnExitQuantity.setOnClickListener {
            activity?.finish()
        }

        btnProceedQuantity.setOnClickListener {
            activity?.showFragment<WelcomeMenuFragment>()//todo
        }
    }

    override fun enableButtonProceed(allowEnableProceedButton: Boolean) {
        btnProceedQuantity.isEnabled = allowEnableProceedButton
        btnProceedQuantity.isActivated = allowEnableProceedButton
    }

    override fun updateValueItemName(value: String) {
        tv_name.text = value
    }

    override fun updateValueItemNameDesc(value: String) {
        tv_name_desc.text = value
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
}
