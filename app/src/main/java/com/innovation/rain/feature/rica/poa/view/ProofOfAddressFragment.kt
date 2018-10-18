package  com.innovation.rain.feature.rica.poa.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.app.utils.startForResult
import com.innovation.rain.feature.rica.address.model.Address
import com.innovation.rain.feature.rica.address.view.ManualAddressFragment
import com.innovation.rain.feature.rica.address.view.ManualAddressFragment.Companion.DATA_KEY
import com.innovation.rain.feature.rica.base.BaseRicaFragment
import com.innovation.rain.feature.rica.poa.adapter.AddressAdapter
import com.innovation.rain.feature.rica.poa.presenter.ProofOfAddressPresenter
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
        startForResult<ManualAddressFragment>(requestCode = REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val address = data?.extras?.getParcelable(DATA_KEY) as Address
            presenter.submitManualAddress(address)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun showAddressList(list: List<String>) {
        adapter.swapData(list)
        btnCaptureManual?.visibility = View.GONE
    }

    override fun onDone() {
        notifyRicaStateDone()
    }

    companion object {
        const val REQUEST_CODE = 1001
    }
}
