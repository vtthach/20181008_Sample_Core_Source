package com.innovation.rain.feature.rica.sample

import com.innovation.rain.R
import com.innovation.rain.feature.rica.base.BaseRicaFragment
import com.innovation.rain.feature.rica.home.presenter.RicaHomePresenter


class SampleFragment : BaseRicaFragment<RicaHomePresenter>() {
    override fun getLoadedStateLayout(): Int {
       return R.layout.fragment_sample
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
