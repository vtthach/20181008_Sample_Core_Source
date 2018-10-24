package  com.innovation.rain.feature.order.create.view

import com.sf0404.core.application.base.presenter.BaseView


interface CreateOrderView : BaseView {

    fun enableButtonProceed(allowEnableProceedButton: Boolean)
    fun updateValueItemPrice(value: String)
    fun updateValueItemTotalPrice(value: String)
    fun updateValueQuantity(value: String)
    fun updateValueSubTotalItem(value: String)

}
