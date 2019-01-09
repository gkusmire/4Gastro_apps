package com.example.grzegorz.a4gastro_waiter_app.viewModel

import android.arch.lifecycle.LiveData

interface ReadyOrderDetailsViewModel {

    fun getOrdersListLiveData(): LiveData<List<Order>>
    fun removeOrderDetail()
    fun setOrderDetailID(orderID: String)

}
