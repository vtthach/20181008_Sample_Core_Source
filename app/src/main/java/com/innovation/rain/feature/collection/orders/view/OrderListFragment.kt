package com.innovation.rain.feature.collection.orders.view

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.app.utils.showFragment
import com.innovation.rain.feature.collection.orders.model.OrderEntity
import com.innovation.rain.feature.collection.orders.presenter.OrderListPresenter
import com.innovation.rain.feature.rica.home.view.RicaHomeFragment
import com.sf0404.core.application.base.fragment.BasePresenterInjectionFragment
import kotlinx.android.synthetic.main.collection_order_list_fragment.*
import javax.inject.Inject


class OrderListFragment : BasePresenterInjectionFragment<OrderListPresenter>(), OrderListView {

    @Inject
    lateinit var viewPresenter: OrderListPresenter

    @Inject
    lateinit var orderListAdapter: OrderListAdapter

    override fun getPresenter() = viewPresenter

    override fun getLayoutId(): Int {
        return R.layout.collection_order_list_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clientName.text = "Tyla's Order"
        btnExit.setOnClickListener {
            activity?.finish()
        }

        orderRv.adapter = orderListAdapter
        orderRv.layoutManager = GridLayoutManager(activity, 3)

        btnProceed.setOnClickListener {
            activity?.showFragment<RicaHomeFragment>()
        }
    }

    override fun showList(list: List<OrderEntity>) {
        orderListAdapter.data = list
        orderListAdapter.notifyDataSetChanged()
    }

}
