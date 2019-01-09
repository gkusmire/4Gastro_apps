package com.example.grzegorz.a4gastro_chef_app.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.grzegorz.a4gastro_chef_app.model.Model
import com.example.grzegorz.a4gastro_chef_app.model.OrderData

class OrderDetailViewModelClass: ViewModel(), OrderDetailViewModel {

    private val orderListLiveData = MutableLiveData<List<Order>>()
    private var orderID: String = ""

    override fun getOrderListLiveData(): LiveData<List<Order>> = orderListLiveData

    override fun realiseOrder() {

        Model.removeReadyOrderDetail(orderID)
    }

    override fun setOrderDetailID(id: String?) {

        if(id == null) return
        orderID = id

        val orderDataList: List<OrderData> = Model.getOrdersAssociatedWithOrderDetail(orderID)
        val orders = mutableListOf<Order>()

        orderDataList.forEach {

            val order = Order()
            order.description = it.description
            order.name = Model.getDishName(it.dishID)
            order.number = it.number
            orders.add(order)
        }
        orderListLiveData.value = orders
    }

}
