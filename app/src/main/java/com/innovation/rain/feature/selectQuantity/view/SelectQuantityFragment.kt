package  com.innovation.rain.feature.selectQuantity.view

import android.os.Bundle
import com.innovation.rain.feature.selectQuantity.presenter.SelectQuantityPresenter
import com.innovation.rain.R
import com.innovation.rain.feature.selectQuantity.model.Item
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_select_quantity.*
import android.widget.ArrayAdapter
import com.innovation.rain.R.id.listItem
import com.sf0404.core.application.base.fragment.BasePresenterInjectionFragment


class SelectQuantityFragment : BasePresenterInjectionFragment<SelectQuantityPresenter>(), SelectQuantityView {

    @Inject
    lateinit var viewPresenter: SelectQuantityPresenter

    override fun getPresenter() = viewPresenter

    override fun getLayoutId(): Int {
        return R.layout.fragment_select_quantity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //initListItem()

    }

    fun initListItem(){
        var item1 = Item("Sim 1", 2.0)
        var item2 = Item("Sim 2", 4.0)
        var item3 = Item("Sim 3", 6.0)
        var item4 = Item("Sim 4", 8.0)

        val list = ArrayList<Item>()
        list.add(item1)
        list.add(item2)
        list.add(item3)
        list.add(item4)

        var listName = getNameFromList(list)

        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, listName)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        listItem.setAdapter(adapter)
    }

    fun getNameFromList(list: ArrayList<Item>): ArrayList<String>{
        var listName = ArrayList<String>()
        if(list.size > 0){
            for(item in list){
                listName.add(item.name)
            }
        }
        return listName
    }
}
