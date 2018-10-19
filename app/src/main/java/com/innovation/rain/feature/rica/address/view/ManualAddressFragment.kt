package  com.innovation.rain.feature.rica.address.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.innovation.rain.R
import com.innovation.rain.feature.rica.address.model.Address
import com.innovation.rain.feature.rica.address.presenter.ManualAddressPresenter
import com.sf0404.core.application.base.fragment.BasePresenterInjectionFragment
import kotlinx.android.synthetic.main.fragment_manual_address.*
import javax.inject.Inject


class ManualAddressFragment : BasePresenterInjectionFragment<ManualAddressPresenter>(), ManualAddressView {
    override fun enableOkButton(enable: Boolean) {
        btnOk.isEnabled = enable
        btnOk.isActivated = enable
    }

    @Inject
    lateinit var viewPresenter: ManualAddressPresenter

    override fun getPresenter() = viewPresenter

    override fun getLayoutId(): Int {
        return R.layout.fragment_manual_address
    }

    val textWatcher = object : TextWatcher {
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
        btnOk.setOnClickListener {
            activity?.run {
                val bundle = Bundle().apply {
                    putParcelable(DATA_KEY, Address(
                            address = addressEt.text.toString(),
                            streetAddress = streetAddressEt.text.toString(),
                            suburb = suburbEt.text.toString(),
                            town = townEt.text.toString(),
                            province = province.selectedItem.toString()))
                }

                val intent = Intent().apply {
                    this.putExtras(bundle)
                }
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
        enableOkButton(false)
    }

    companion object {
        const val DATA_KEY = "AddressData"
    }

}
