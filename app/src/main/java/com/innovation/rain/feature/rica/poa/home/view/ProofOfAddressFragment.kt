package  com.innovation.rain.feature.rica.poa.home.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.app.utils.showFragment
import com.innovation.rain.feature.rica.base.BaseRicaFragment
import com.innovation.rain.feature.rica.home.view.RicaHomeFragment
import com.innovation.rain.feature.rica.poa.address.view.ManualAddressFragment
import com.innovation.rain.feature.rica.poa.home.adapter.AddressAdapter
import com.innovation.rain.feature.rica.poa.home.presenter.ProofOfAddressPresenter
import com.innovation.rain.feature.rica.poa.scan.view.ScanPOAFragment
import kotlinx.android.synthetic.main.fragment_proof_of_address.*
import javax.inject.Inject

class ProofOfAddressFragment : BaseRicaFragment<ProofOfAddressPresenter>(), ProofOfAddressView, AddressAdapter.OnItemClicked {

    override fun showManualCaptureBtn() {
        btnCaptureManual.visibility = View.VISIBLE
    }

    override fun onItemClicked(position: Int) {
        presenter.handleItemSelected(position)
        enableButtonProceed(true)
    }

    override fun getPreLoadStateTitle() = getString(R.string.poa_title)

    override fun getDoneStateTitle() = getString(R.string.poa_verified_title)

    override fun getLoadedStateLayout(): Int {
        return R.layout.fragment_proof_of_address
    }

    override fun onProceedButtonClicked() {
        viewPresenter.handleProceedButtonClicked()
    }

    @Inject
    lateinit var viewPresenter: ProofOfAddressPresenter

    override fun getPresenter() = viewPresenter

    private val adapter = AddressAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addressRv.layoutManager = LinearLayoutManager(context)
        addressRv.adapter = adapter
        adapter.listener = this
        btnCaptureManual.setOnClickListener {
            showCaptureManualScreen()
        }
    }

    override fun showCaptureManualScreen() {
        showFragment<ManualAddressFragment>(requestCode = RicaHomeFragment.REQUEST_CODE)
    }

    override fun showAddressList(list: List<String>) {
        adapter.swapData(list)
        btnCaptureManual?.visibility = View.GONE
    }

    override fun onDone() {
        showFragment<ScanPOAFragment>(requestCode = RicaHomeFragment.REQUEST_CODE)
    }
}
