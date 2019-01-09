package com.example.grzegorz.a4gastro_waiter_app.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.grzegorz.a4gastro_waiter_app.model.Model
import com.example.grzegorz.a4gastro_waiter_app.model.OrderData
import com.example.grzegorz.a4gastro_waiter_app.model.TableData

class TableDetailsViewModelClass: ViewModel(), TableDetailsViewModel {

    private val tablesLiveData: LiveData<List<TableData>> = Model.getTablesLiveData()
    private val ordersLiveData: LiveData<List<OrderData>> = Model.getOrdersLiveData()

    private val tableDescriptionLiveData = MutableLiveData<String>()
    private val orderListLiveData = MutableLiveData<List<Order>>()
    private var tableID: String = ""

    override fun setTableID(id: String) {
        tableID = id

        updateDescriptionLiveData()
        updateOrderListLiveData()
    }

    override fun generateNewTableID(number: String) {

        tableID = Model.generateNewTable(number)

        orderListLiveData.value = emptyList()
        tableDescriptionLiveData.value = number
    }

    override fun getTableDescription(): LiveData<String> = tableDescriptionLiveData

    override fun printBill() {
        Model.printBill(tableID)
    }

    override fun getOrderList(): LiveData<List<Order>> = orderListLiveData

    override fun getTableID(): String = tableID

    private fun updateDescriptionLiveData() {

        tableDescriptionLiveData.value = Model.getTable(tableID).description
    }

    private fun updateOrderListLiveData() {

        val orders = Model.getOrdersAssociatedWithTableID(tableID)

        orderListLiveData.value = orders
    }
}
