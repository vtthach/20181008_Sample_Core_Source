package  com.innovation.rain.feature.rica.poa.address.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.innovation.rain.R
import com.innovation.rain.app.utils.showFragment
import com.innovation.rain.feature.rica.poa.address.presenter.ManualAddressPresenter
import com.innovation.rain.feature.rica.poa.scan.view.ScanPOAFragment
import com.sf0404.core.application.base.fragment.BasePresenterInjectionFragment
import kotlinx.android.synthetic.main.fragment_manual_address.*
import javax.inject.Inject


class ManualAddressFragment : BasePresenterInjectionFragment<ManualAddressPresenter>(), ManualAddressView {

    @Inject
    lateinit var mPresenter: ManualAddressPresenter

    override fun getPresenter() = mPresenter

    override fun getLayoutId() = R.layout.fragment_manual_address

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //not implement
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //not implement
        }

        override fun afterTextChanged(p0: Editable?) {
            validate()
        }

    }

    private fun validate() {
        presenter.validate(address = addressEt.text.toString(),
                streetAddress = streetAddressEt.text.toString(),
                suburb = suburbEt.text.toString(),
                town = townEt.text.toString(),
                province = province.selectedItem.toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ArrayAdapter.createFromResource(context!!, R.array.arr_south_african_provinces, R.layout.province_item)
        province.adapter = adapter
        streetAddressEt.addTextChangedListener(textWatcher)
        suburbEt.addTextChangedListener(textWatcher)
        townEt.addTextChangedListener(textWatcher)
        province.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                validate()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                validate()
            }

        }
        btnExit.setOnClickListener {
            activity?.run {
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        }
        btnOk.setOnClickListener { mPresenter.submitManualAddress() }
        enableOkButton(false)
    }

    override fun enableOkButton(enable: Boolean) {
        btnOk.isEnabled = enable
        btnOk.isActivated = enable
    }

    override fun gotoScanPOAScreen() {
        showFragment<ScanPOAFragment>(flag = Intent.FLAG_ACTIVITY_FORWARD_RESULT)
        finish()
    }
}
