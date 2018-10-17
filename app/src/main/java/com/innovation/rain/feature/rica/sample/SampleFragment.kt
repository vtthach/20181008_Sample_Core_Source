package com.innovation.rain.feature.rica.sample

import com.innovation.rain.feature.rica.base.BaseRicaFragment
import com.innovation.rain.feature.rica.home.presenter.RicaHomePresenter


class SampleFragment : BaseRicaFragment<RicaHomePresenter>() {
    override fun getLoadedStateLayout(): Int {
        TODO("return layout of loaded state")
        return 0;
//       return R.layout.sample_view
    }

    override fun getPreLoadStateTitle(): String {
        return "Sample preload title"
    }

    override fun getDoneStateTitle(): String {
        return "Sample done title"
    }

    override fun getPresenter(): RicaHomePresenter? {
        return null
    }


    override fun onProceedButtonClicked() {
      //TODO
    }

}
