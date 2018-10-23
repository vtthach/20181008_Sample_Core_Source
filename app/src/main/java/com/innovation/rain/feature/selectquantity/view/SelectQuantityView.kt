package  com.innovation.rain.feature.selectquantity.view

import com.sf0404.core.application.base.presenter.BaseView


interface SelectQuantityView : BaseView {

    fun enableButtonProceed(allowEnableProceedButton: Boolean)
    fun updateValueItemName(value: String)
    fun updateValueItemNameDesc(value: String)
    fun updateValueItemPrice(value: String)
    fun updateValueItemTotalPrice(value: String)
    fun updateValueQuantity(value: String)
    fun updateValueSubTotalItem(value: String)

}
