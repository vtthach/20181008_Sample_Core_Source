package  com.innovation.rain.feature.rica.address.view

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.innovation.rain.R
import com.innovation.rain.app.base.fragment.BasePresenterInjectionFragment
import com.innovation.rain.feature.rica.address.presenter.ManualAddressPresenter
import kotlinx.android.synthetic.main.fragment_manual_address.*

import javax.inject.Inject


class ManualAddressFragment : BasePresenterInjectionFragment<ManualAddressPresenter>(), ManualAddressView {
    override fun enableOkButton() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Inject
    lateinit var viewPresenter: ManualAddressPresenter

    override fun getPresenter() = viewPresenter

    override fun getLayoutId(): Int {
        return R.layout.fragment_manual_address
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter =  ArrayAdapter.createFromResource(context!!,R.array.arr_south_african_provinces, R.layout.province_item)
        province.adapter = adapter
    }

}
