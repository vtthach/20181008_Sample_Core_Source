package  com.innovation.rain.feature.selectquantity.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.innovation.rain.R
import com.innovation.rain.feature.selectquantity.presenter.SelectQuantityPresenter
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_select_quantity.*
import com.sf0404.core.application.base.fragment.BasePresenterInjectionFragment


class SelectQuantityFragment : BasePresenterInjectionFragment<SelectQuantityPresenter>(), SelectQuantityView {

    @Inject
    lateinit var viewPresenter: SelectQuantityPresenter

    override fun getPresenter() = viewPresenter

    override fun getLayoutId(): Int {
        return R.layout.fragment_select_quantity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*Find the id of spinner*/
        val spinner = itemDropDown
        val listItemsTxt = arrayOf("R50 once off", "R100 once off", "R200 once off", "R300 once off")
        /*set an adapter with strings array*/
        spinner.adapter = ArrayAdapter(activity, R.layout.support_simple_spinner_dropdown_item, listItemsTxt)


        /*var spinnerAdapter = ItemAdapter(activity, listItemsTxt)
        var spinner: Spinner = itemDropDown as Spinner
        spinner?.adapter = spinnerAdapter*/

        /*set click listener*/
       spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val num = when (spinner.selectedItem.toString()) {
                    "R50 once off" -> {
                        tv_name.text = "SIM +10 data days"
                        tv_name_desc.text = "R50 a gig"
                        tv_price.text = "R50.00"
                    }
                    "R100 once off" -> {
                        tv_name.text = "SIM +20 data days"
                        tv_name_desc.text = "R100 a gig"
                        tv_price.text = "R100.00"
                    }
                    "R200 once off" -> {
                        tv_name.text = "SIM +30 data days"
                        tv_name_desc.text = "R200 a gig"
                        tv_price.text = "R200.00"
                    }
                    "R300 once off" -> {
                        tv_name.text = "SIM +40 data days"
                        tv_name_desc.text = "R300 a gig"
                        tv_price.text = "R300.00"
                    }
                    else -> {
                        tv_name.text = ""
                        tv_name_desc.text = ""
                        tv_price.text = ""
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        /*button.setOnClickListener {
            if (
                    editText2.text.toString().length > 0 &&
                    editText.text.toString().length > 0) {
                val num2 = editText.text.toString().toDouble()
                val num1 = editText2.text.toString().toDouble()
                val num = num1/num2
                textView.setText("$num moles")
            }
            else {
                textView.setText("Please Enter a correct value")
            }
        }*/

    }

    fun initItemList(){

        val listItemsTxt = arrayOf("Residential", "Commercial")

        /*var spinnerAdapter = ItemAdapter(activity, listItemsTxt)
        var spinner: Spinner = itemDropDown as Spinner
        spinner?.adapter = spinnerAdapter*/
    }


}
