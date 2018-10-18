package  com.innovation.rain.feature.collection.simdispenser.view

import com.sf0404.core.application.base.fragment.BasePresenterInjectionFragment
import com.innovation.rain.feature.collection.simdispenser.presenter.SimDispenserPresenter

import javax.inject.Inject


class SimDispenserFragment : BasePresenterInjectionFragment<SimDispenserPresenter>(), SimDispenserView {

    @Inject
    lateinit var viewPresenter: SimDispenserPresenter

    override fun getPresenter() = viewPresenter

    override fun getLayoutId(): Int {
        return R.layout.fragment_sim_dispenser
        //TODO: provide layout id
    }

}
