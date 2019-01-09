package com.example.grzegorz.a4gastro_waiter_app.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.grzegorz.a4gastro_waiter_app.model.Model

class ReadyOrderDetailsViewModelClass: ViewModel(), ReadyOrderDetailsViewModel {

    private var orderDetailID: String = ""
    private val readyOrdersLiveData = MutableLiveData<List<Order>>()

    override fun getOrdersListLiveData(): LiveData<List<Order>> = readyOrdersLiveData

    override fun removeOrderDetail() {

        Model.removeOrderDetailFromReadyOrders(orderDetailID)
    }

    override fun setOrderDetailID(orderID: String) {

        orderDetailID = orderID
        updateReadyOrdersLiveData()
    }

    private fun updateReadyOrdersLiveData() {

        val orderDetail = Model.getOrderDetailByItsID(orderDetailID)

        val orderDataList = Model.getOrdersAssociatedWithOrderDetail(orderDetail)
        val orders = mutableListOf<Order>()

        orderDataList.forEach {
            orders.add(Model.getOrder(orderDetail.tableID!!, it))
        }

        readyOrdersLiveData.value = orders
    }

}
